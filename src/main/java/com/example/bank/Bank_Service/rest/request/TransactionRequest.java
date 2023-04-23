package com.example.bank.Bank_Service.rest.request;

import com.example.bank.Bank_Service.model.CardDto;
import com.example.bank.Bank_Service.model.OrderItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TransactionRequest {
    private BigDecimal transactionAmount;
    private Long customerId;
    private Long vendorId;
    private String customerName;
    private List<OrderItemDto> orderItem;
    private CardDto cardDetails;
    private Date date;
}