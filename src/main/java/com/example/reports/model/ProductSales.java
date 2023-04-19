package com.example.reports.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class ProductSales {
    @Id
    @SequenceGenerator(
            name = "productSalesSeq",
            sequenceName = "productSalesSeq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productSalesSeq"
    )
    private long productSalesId;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "transaction_id",
            referencedColumnName = "transactionId"
    )
    private Transaction transaction;

    private long productId;
    private String productName;
    private String category;
    private long vendorId;
    private String vendorName;
    private int quantity;
    private double pricePerUnit;
    private double total;

}
