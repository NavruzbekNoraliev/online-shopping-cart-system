package com.example.bank.Bank_Service.client;

import com.example.bank.Bank_Service.rest.request.RequestedCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "transactions", url = "http://localhost:8080/api/v1")
public interface PaymentClient {

    @GetMapping("/cards/{number}")
    RequestedCard getCardDetails(@PathVariable String number);
}
