package com.example.payment.paymentservice.rest.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@Builder
public class UtilityPaymentResponse {
    private String message;
    private String transactionId;
}
