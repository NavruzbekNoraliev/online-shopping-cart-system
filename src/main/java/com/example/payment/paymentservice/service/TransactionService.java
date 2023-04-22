package com.example.payment.paymentservice.service;

import com.example.payment.paymentservice.KafkaProducer;
import com.example.payment.paymentservice.client.BankClient;
import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import com.example.payment.paymentservice.model.mapper.TransactionMapper;
import com.example.payment.paymentservice.repository.TransactionRepository;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;

    private TransactionMapper transactionMapper = new TransactionMapper();

    @Autowired
    private BankClient bankClient;




    private final KafkaProducer kafkaProducer;

    public void broadcastPaymentComplete(TransactionDto transactionDto){
        ObjectMapper om = new ObjectMapper();
        String transactionJson = null;
        try {
            transactionJson = om.writeValueAsString(transactionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service message: \n"+transactionJson);
        kafkaProducer.sendMessage("my-topic", transactionJson);
    }




    public TransactionResponse checkCard(TransactionRequest transactionRequest) {
        TransactionResponse balance = bankClient.getAcceptance(transactionRequest);
        return balance;
        // do something with the balance
    }

    public ResponseEntity utilPayment(TransactionRequest transactionRequest , Boolean isVendor){
        log.info("Transaction processing {}", transactionRequest.toString());

        TransactionResponse returnedTransaction = checkCard(transactionRequest);

        TransactionEntity entity = new TransactionEntity();
        BeanUtils.copyProperties(transactionRequest, entity);
        entity.setCardNumber(transactionRequest.getCardDetails().getCardNumber());
        entity.setTransactionStatus(returnedTransaction.getTransactionStatus());
        entity.setTransactionNumber(returnedTransaction.getTransactionId());
        if(isVendor){
            entity.setVendorId(transactionRequest.getVendorId());
            entity.setTransactionAmount(BigDecimal.valueOf(20000));
        }
        transactionRepository.save(entity);

        return ResponseEntity.ok(TransactionResponse.builder().transactionStatus(returnedTransaction.getTransactionStatus())
                .transactionId(returnedTransaction.getTransactionId()).message(returnedTransaction.getMessage()).build());
    }

    public List<TransactionDto> readTransactions(Pageable pageable) {
        Page<TransactionEntity> allUtilPayments = transactionRepository.findAll(pageable);
        return transactionMapper.convertToDtoList(allUtilPayments.getContent());
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