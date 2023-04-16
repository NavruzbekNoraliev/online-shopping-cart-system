package com.example.bank.Bank_Service.rest.request;

import com.example.bank.Bank_Service.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class BankRequest {
    private Long id;
    private String cardId;
    private String cardNumber;
    private String nameOnCard;
    private String cvv;
    private BigDecimal IssuedValue;
    private BigDecimal CurrentValue;
    private String operationMode;
}
