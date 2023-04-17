package com.example.reports.service;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.Transaction;
import com.example.reports.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        Transaction transaction = Transaction.builder()
                .date(transactionDTO.getDate())
                .productSales(transactionDTO.getProductSales())
                .build();
        transactionRepository.save(transaction);
        return transactionDTO;
    }
}
