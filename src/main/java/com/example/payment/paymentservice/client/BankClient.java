package com.example.payment.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "bank", url = "http://localhost:8082")
public interface BankClient {

    @GetMapping("/banking")
    String getBalance();
}