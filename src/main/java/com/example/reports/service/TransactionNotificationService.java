package com.example.reports.service;

import com.example.reports.dto.TransactionRequest;
import com.example.reports.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionNotificationService {
    private final KafkaProducer kafkaProducer;

    public void broadcastPaymentComplete(TransactionRequest transactionDto){
        //Object object = new Object(transactionDto);
        ObjectMapper om = new ObjectMapper();
        String transactionJson = null;
        try {
            transactionJson = om.writeValueAsString(transactionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service message: \n"+transactionJson);
        kafkaProducer.sendMessage("transactions", transactionJson);
    }
}
