package com.example.payment.paymentservice.client;

import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank", url = "http://localhost:8082")
public interface BankClient {

    @PostMapping("/banking")
    TransactionResponse getAcceptance(@RequestBody TransactionRequest transactionRequest);

}