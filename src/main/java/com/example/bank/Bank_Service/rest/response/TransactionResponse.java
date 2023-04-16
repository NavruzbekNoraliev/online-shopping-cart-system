package com.example.bank.Bank_Service.rest.response;

import com.example.bank.Bank_Service.model.TransactionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private TransactionStatus transactionStatus;
    private String transactionId;
}