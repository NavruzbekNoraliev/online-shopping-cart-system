package com.example.payment.paymentservice.model.entity;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class CardEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "cvv")
    private Integer cvv;

    @Column(name = "issued_value")
    private BigDecimal IssuedValue;

    @Column(name = "current_value")
    private BigDecimal CurrentValue;

    @Column(name = "operation_mode")
    private String operationMode;

    @Column(name = "exp_day")
    private String expDay;

    @Column(name = "exp_month")
    private String expMonth;

}
