package com.example.payment.paymentservice.model.dto;

import com.example.payment.paymentservice.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UtilityPayment {
    private Long id;
    private BigDecimal amount;
    private String description;
    private Date date;
    private TransactionStatus status;
}
