package com.example.reports.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transactionSeq",
            sequenceName = "transactionSeq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transactionSeq"
    )
    long transactionId;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "transaction_id",
            referencedColumnName = "transactionId"
    )
    List<ProductSales> productSales;
    Date date;
    long userId;
    String userName;

}
