package com.example.payment.paymentservice.service;

import com.example.payment.paymentservice.client.BankClient;
import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import com.example.payment.paymentservice.model.mapper.TransactionMapper;
import com.example.payment.paymentservice.repository.TransactionRepository;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private TransactionMapper transactionMapper = new TransactionMapper();

    @Autowired
    private BankClient bankClient;

    public TransactionResponse checkCard(TransactionRequest transactionRequest) {
        TransactionResponse balance = bankClient.getAcceptance(transactionRequest);
        return balance;
        // do something with the balance
    }

    public TransactionResponse utilPayment(TransactionRequest transactionRequest) {
        log.info("Transaction processing {}", transactionRequest.toString());

        TransactionResponse returnedTransaction = checkCard(transactionRequest);

        TransactionEntity entity = new TransactionEntity();
        BeanUtils.copyProperties(transactionRequest, entity);
        entity.setTransactionStatus(returnedTransaction.getTransactionStatus());
        entity.setTransactionNumber(returnedTransaction.getTransactionId());
        TransactionEntity optUtilPayment = transactionRepository.save(entity);

        return TransactionResponse.builder().transactionStatus(optUtilPayment.getTransactionStatus())
                .transactionId(optUtilPayment.getTransactionNumber()).build();
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