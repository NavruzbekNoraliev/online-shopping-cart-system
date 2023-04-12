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
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "transaction_address")
    private String transactionAddress;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_active_indicator")
    private String vendorActiveIndicator;

    private Date date;
}