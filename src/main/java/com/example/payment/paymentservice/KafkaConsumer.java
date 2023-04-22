package com.example.payment.paymentservice;

import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
//    @KafkaListener(topics = "my-topic")
//    public void consume(String message){
//        System.out.println("Kafka Consumer\n"+message);
//        ObjectMapper om = new ObjectMapper();
//        TransactionDto transaction;
//        try {
//            transaction = om.readValue(message, TransactionDto.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(transaction.getCardNumber());
//    }

}

