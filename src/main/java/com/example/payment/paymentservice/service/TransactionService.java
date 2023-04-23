package com.example.payment.paymentservice.service;

import com.example.payment.paymentservice.KafkaProducer;
import com.example.payment.paymentservice.client.BankClient;
import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.dto.OrderItemDto;
import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.model.entity.ClientRevenueEntity;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import com.example.payment.paymentservice.model.entity.VendorRevenueEntity;
import com.example.payment.paymentservice.model.mapper.TransactionMapper;
import com.example.payment.paymentservice.repository.ClientRevenueRepository;
import com.example.payment.paymentservice.repository.TransactionRepository;
import com.example.payment.paymentservice.repository.VendorRevenueRepository;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;


    private final VendorRevenueRepository vendorRevenueRepository;
    private final ClientRevenueRepository clientRevenueRepository;
    @Autowired
    private CardService cardService;

    private TransactionMapper transactionMapper = new TransactionMapper();

    @Autowired
    private BankClient bankClient;




    private final KafkaProducer kafkaProducer;

    public void broadcastPaymentComplete(TransactionRequest transactionRequest){
        ObjectMapper om = new ObjectMapper();
        String transactionJson = null;
        try {
            transactionJson = om.writeValueAsString(transactionRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service message: \n"+transactionJson);
        kafkaProducer.sendMessage("transactions", transactionJson);
    }




    public TransactionResponse checkCard(TransactionRequest transactionRequest) {
        TransactionResponse balance = bankClient.getAcceptance(transactionRequest);
        return balance;
        // do something with the balance
    }

    public List<Long> getUniqueVendorIds(List<OrderItemDto> orderItems) {
        Set<Long> vendorIds = new HashSet<>();
        for (OrderItemDto orderItem : orderItems) {
            vendorIds.add(orderItem.getVendorId());
        }
        return new ArrayList<>(vendorIds);
    }


    public void updateClientOnly(){
        ClientRevenueEntity clientRevenueEntity = clientRevenueRepository.findAll().stream().findFirst().orElse(null);
        if (clientRevenueEntity != null) {
            // Update the revenue for the existing client
            clientRevenueEntity.setRevenue(clientRevenueEntity.getRevenue().add(BigDecimal.valueOf(20000)));
            clientRevenueRepository.save(clientRevenueEntity);
        } else {
            // Create a new client with the revenue
            clientRevenueEntity = new ClientRevenueEntity();
            clientRevenueEntity.setClientId(1L); // assuming there is only one client
            clientRevenueEntity.setRevenue(BigDecimal.valueOf(20000));
            clientRevenueRepository.save(clientRevenueEntity);
        }
    }
    @Transactional
    public void updateRevenue(List<Long> vendorIds, BigDecimal totalTransactionAmount) {
        // Calculate the revenue for the vendors and the client
       // BigDecimal vendorRevenue = totalTransactionAmount.multiply(new BigDecimal("0.8"));
        BigDecimal vendorRevenue = totalTransactionAmount.multiply(new BigDecimal("0.8")).divide(new BigDecimal(vendorIds.size()));

        BigDecimal clientRevenue = totalTransactionAmount.multiply(new BigDecimal("0.2"));

        // Update the revenue for each vendor
        for (Long vendorId : vendorIds) {
            VendorRevenueEntity vendorRevenueEntity = vendorRevenueRepository.findById(vendorId).orElse(null);
            if (vendorRevenueEntity != null) {
                // Update the revenue for the existing vendor
                vendorRevenueEntity.setRevenue(vendorRevenueEntity.getRevenue().add(vendorRevenue));
                vendorRevenueRepository.save(vendorRevenueEntity);
            } else {
                // Create a new vendor with the revenue
                vendorRevenueEntity = new VendorRevenueEntity();
                vendorRevenueEntity.setVendorId(vendorId);
                vendorRevenueEntity.setRevenue(vendorRevenue);
                vendorRevenueRepository.save(vendorRevenueEntity);
            }
        }

        // Update the revenue for the client
        ClientRevenueEntity clientRevenueEntity = clientRevenueRepository.findAll().stream().findFirst().orElse(null);
        if (clientRevenueEntity != null) {
            // Update the revenue for the existing client
            clientRevenueEntity.setRevenue(clientRevenueEntity.getRevenue().add(clientRevenue));
            clientRevenueRepository.save(clientRevenueEntity);
        } else {
            // Create a new client with the revenue
            clientRevenueEntity = new ClientRevenueEntity();
            clientRevenueEntity.setClientId(1L); // assuming there is only one client
            clientRevenueEntity.setRevenue(clientRevenue);
            clientRevenueRepository.save(clientRevenueEntity);
        }
    }
    public ResponseEntity utilPayment(TransactionRequest transactionRequest , Boolean isVendor){
        log.info("Transaction processing {}", transactionRequest.toString());

        if(isVendor){
            transactionRequest.setTransactionAmount(BigDecimal.valueOf(20000));
        }

        TransactionResponse returnedTransaction = checkCard(transactionRequest);

        TransactionEntity entity = new TransactionEntity();
        BeanUtils.copyProperties(transactionRequest, entity);
        entity.setCardNumber(transactionRequest.getCardDetails().getCardNumber());
        entity.setTransactionStatus(returnedTransaction.getTransactionStatus());
        entity.setTransactionNumber(returnedTransaction.getTransactionId());

        if(isVendor){
            entity.setVendorId(transactionRequest.getVendorId());
            entity.setTransactionAmount(BigDecimal.valueOf(20000));

            updateClientOnly();
        }else {
            entity.setUserId(transactionRequest.getCustomerId());

            if(returnedTransaction.getTransactionStatus() == TransactionStatus.TS){
                List<Long> ids =  getUniqueVendorIds(transactionRequest.getOrderItem());

                updateRevenue(ids , transactionRequest.getTransactionAmount());
            }
        }
        if(returnedTransaction.getTransactionId() != null){
            if(returnedTransaction.getTransactionStatus() == TransactionStatus.TS){
                cardService.deductFromCardTotalAmount(entity.getCardNumber() , entity.getTransactionAmount());
            }

            transactionRepository.save(entity);
        }



        broadcastPaymentComplete(transactionRequest);


        return ResponseEntity.ok(TransactionResponse.builder().transactionStatus(returnedTransaction.getTransactionStatus())
                .transactionId(returnedTransaction.getTransactionId()).message(returnedTransaction.getMessage()).build());
    }

    public List<TransactionDto> readTransactions() {
        Page<TransactionEntity> allUtilPayments = transactionRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        return transactionMapper.convertToDtoList(allUtilPayments.getContent());
    }
    public List<TransactionDto> readTransactionsByCustomerId(Long id) {
        List<TransactionEntity> allCustomerTransactions = transactionRepository.findByUserId(id);
        return transactionMapper.convertToDtoList(allCustomerTransactions);
    }

    public List<VendorRevenueEntity> readAllVendorsRevenues() {
        List<VendorRevenueEntity> allVendorsRevenues = vendorRevenueRepository.findAll();
        return allVendorsRevenues;
    }

    public VendorRevenueEntity readVendorsRevenuesById(Long id) {
        VendorRevenueEntity vendorsRevenues = vendorRevenueRepository.findByVendorId(id);
        return vendorsRevenues;
    }

    public List<ClientRevenueEntity> readClientRevenue() {
        List<ClientRevenueEntity> clientRevenue = clientRevenueRepository.findAll();
        return clientRevenue;
    }


    public List<TransactionDto> readTransactionsByVendorId(Long id) {
        List<TransactionEntity> allVendorTransactions = transactionRepository.findByVendorId(id);
        return transactionMapper.convertToDtoList(allVendorTransactions);
    }

    public TransactionDto getTransactionById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<TransactionEntity> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            return transactionMapper.convertToDto(optionalTransaction.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}