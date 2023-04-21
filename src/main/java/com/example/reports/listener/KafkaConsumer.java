package com.example.reports.listener;

import com.example.reports.model.Transaction;
import com.example.reports.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    TransactionService transactionService;

    @KafkaListener(topics = "transaction", groupId = "group_id")
    public void consume(Transaction transaction) {
        transactionService.createTransaction(transaction);
    }
}
