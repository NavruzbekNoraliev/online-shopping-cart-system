package com.example.reports.service;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.Transaction;

public interface TransactionService {
    public TransactionDTO createTransaction(Transaction transaction);
}
