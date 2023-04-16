package com.example.bank.Bank_Service.client;

import com.example.bank.Bank_Service.rest.request.BankRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "transactions", url = "http://localhost:8081")
public interface PaymentClient {

    @GetMapping("/cards/{cardId}")
    BankRequest getCardDetails(@PathVariable String cardId);
}
