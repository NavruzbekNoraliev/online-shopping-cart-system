package com.example.payment.paymentservice.rest.response;

import com.example.payment.paymentservice.model.TransactionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private TransactionStatus transactionStatus;
    private String transactionId;
}
