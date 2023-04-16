package com.example.bank.Bank_Service.rest.request;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long id;
    private String cardId;
    private String amount;
}