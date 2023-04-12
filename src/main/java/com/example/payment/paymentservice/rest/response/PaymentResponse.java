package com.example.payment.paymentservice.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String message;
    private String transactionId;
}
