package com.example.bank.Bank_Service.model.entity;


import com.example.bank.Bank_Service.model.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "transaction")
public class MasterBalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;


    @Column(name = "card_balance")
    private Integer cardBalance;

    @Column(name = "transaction_value")
    private BigDecimal transactionValue;


    @Column(name = "transaction_number")
    private String transactionNumber;

    private Date date;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;


}