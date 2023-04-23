package com.example.bank.Bank_Service.rest.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestedCard {
    private Long id;
    private String cardId;
    private String cardNumber;
    private String nameOnCard;
    private String cvv;
    private BigDecimal IssuedValue;
    private BigDecimal CurrentValue;
    private String operationMode;
    private String expYear;
    private String expMonth;
}
