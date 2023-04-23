package com.example.payment.paymentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "client_revenue")
public class ClientRevenueEntity {

    @Id
    private Long clientId;

    @Column(name = "revenue")
    private BigDecimal revenue ;


}