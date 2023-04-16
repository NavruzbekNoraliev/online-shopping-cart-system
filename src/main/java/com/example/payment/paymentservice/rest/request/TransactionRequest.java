package com.example.payment.paymentservice.rest.request;

import com.example.payment.paymentservice.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionRequest {
    private Long id;
    private String cardId;
    private String amount;
}