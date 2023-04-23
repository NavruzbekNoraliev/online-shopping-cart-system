package com.example.bank.Bank_Service;

import com.example.bank.Bank_Service.model.entity.TransactionDto;
import com.example.bank.Bank_Service.rest.request.TransactionRequest;
import com.example.bank.Bank_Service.rest.response.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
//    @KafkaListener(topics = "transactions")
//    public void consume(String message){
//        System.out.println("Kafka Consumer\n"+message);
//        ObjectMapper om = new ObjectMapper();
//        TransactionRequest transactionRequest;
//        try {
//            transactionRequest = om.readValue(message, TransactionRequest.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(transactionRequest.toString());
//    }

}

