package com.example.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private BigDecimal transactionAmount;
    private Long customerId;
    private String customerName;
    private List<OrderItemDto> orderItem;
    private CardEntity cardDetails;
    private Date date;
}
