package com.example.reports.service;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(Transaction transaction);
    List<Transaction> createOrderHistory(long userId);
}
