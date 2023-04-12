package com.example.payment.paymentservice.rest.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentRequest {
    private Long id;
    private BigDecimal amount;
    private String description;
    private Date date;
}