package com.example.reports.service;

import com.example.reports.dto.Mapper;
import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.ProductSales;
import com.example.reports.model.Transaction;
import com.example.reports.repository.ProductSalesRepository;
import com.example.reports.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    Mapper mapper;

    public TransactionDTO createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        return mapper.transactionToDto(transaction);
    }
}
