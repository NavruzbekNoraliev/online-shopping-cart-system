package com.example.payment.paymentservice.rest.request;

import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.dto.ProductDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private CardEntity cardDetails;
    private Date date;
}