package com.example.reports.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
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
    long productSalesId;
    long transactionId;

    long productId;
    String productName;
    long vendorId;
    String vendorName;
    int quantity;
    double pricePerUnit;

}
