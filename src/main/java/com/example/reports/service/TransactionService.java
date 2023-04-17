package com.example.reports.service;

import com.example.reports.dto.TransactionDTO;
import org.springframework.stereotype.Service;

public interface TransactionService {
    public TransactionDTO createTransaction(TransactionDTO transactionDTO);
}
