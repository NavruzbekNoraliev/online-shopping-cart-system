package com.example.bank.Bank_Service.rest.request;

import com.example.bank.Bank_Service.model.CardDto;
import com.example.bank.Bank_Service.model.ProductDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TransactionRequest {
    private BigDecimal transactionAmount;
    private Long vendorId;
    private Long userId;
    private String vendorActiveIndicator;
    private List<ProductDto> products;
    private CardDto cardDetails;
    private Date date;
}