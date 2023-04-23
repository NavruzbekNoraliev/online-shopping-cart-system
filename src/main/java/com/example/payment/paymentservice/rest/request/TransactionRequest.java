package com.example.payment.paymentservice.rest.request;
import com.example.payment.paymentservice.model.dto.OrderItemDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
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
    private CardEntity cardDetails;
    private Date date;
}