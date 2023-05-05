package com.example.bank.Bank_Service.model.entity;


import com.example.bank.Bank_Service.model.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_balance")
public class MasterBalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;


    @Column(name = "card_balance")
    private BigDecimal cardBalance;

    @Column(name = "transaction_value")
    private String transactionValue;


    @Column(name = "transaction_number")
    private String transactionNumber;

    private LocalDate date;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;


}