package com.example.payment.paymentservice.model.dto;

import com.example.payment.paymentservice.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private String cardNumber;
    private String transactionNumber;
    private BigDecimal transactionAmount;
    private Long vendorId;
    private Long userId;
    private String vendorActiveIndicator;
    private Date date;
    //private TransactionStatus transactionStatus;
}
