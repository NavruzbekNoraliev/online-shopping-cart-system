package com.example.payment.paymentservice.rest.response;

import com.example.payment.paymentservice.model.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private Long id;
    private String cardId;
    private String cardNumber;
    private String nameOnCard;
    private Integer cvv;
    private BigDecimal IssuedValue;
    private BigDecimal CurrentValue;
    private String operationMode;
    private String expYear;
    private String expMonth;
}

