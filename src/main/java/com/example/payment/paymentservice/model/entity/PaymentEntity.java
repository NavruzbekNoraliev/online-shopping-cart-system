package com.example.payment.paymentservice.model.entity;

import com.example.payment.paymentservice.model.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "utility_payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String description;
    private Date date;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}