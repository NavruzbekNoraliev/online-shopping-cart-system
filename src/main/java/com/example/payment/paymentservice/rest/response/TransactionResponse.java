package com.example.payment.paymentservice.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private String message;
    private String transactionId;
}
