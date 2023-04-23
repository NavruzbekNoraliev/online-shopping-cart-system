package com.example.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    private Long id;
    private String cardId;
    private String cardNumber;
    private String nameOnCard;
    private Integer cvv;
    private BigDecimal IssuedValue;
    private BigDecimal CurrentValue;
    private String operationMode;
    private String expDay;
    private String expMonth;
}
