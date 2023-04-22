package com.example.reports.listener;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.Transaction;
import com.example.reports.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    TransactionService transactionService;

//    @KafkaListener(topics = "transaction", groupId = "group_id")
//    public void consume(Transaction transaction) {
//        transactionService.createTransaction(transaction);
//    }

    @KafkaListener(topics = "transactions")
    public void consume(String message){
        System.out.println("Kafka Consumer\n"+message);
        ObjectMapper om = new ObjectMapper();
        TransactionDTO transaction;
        try {
            transaction = om.readValue(message, TransactionDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
