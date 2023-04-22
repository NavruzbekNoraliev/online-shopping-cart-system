package com.example.bank.Bank_Service.model;


import lombok.Data;

import java.math.BigDecimal;


@Data
public class CardDto {
    private String cardId;
    private String cardNumber;
    private String nameOnCard;
    private Integer cvv;
    private BigDecimal IssuedValue;
    private BigDecimal CurrentValue;
    private String operationMode;
}
