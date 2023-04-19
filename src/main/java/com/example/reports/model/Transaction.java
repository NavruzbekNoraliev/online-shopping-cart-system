package com.example.reports.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private long transactionId;

//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "transaction_id",
//            referencedColumnName = "transactionId"
//    )
    @OneToMany(
            mappedBy = "transaction"
    )
    private List<ProductSales> productSales;
    private Date date;
    private long userId;
    private String userName;

    public List<Report> createReport(){
        List<Report> reports = new ArrayList<>();
        for(ProductSales ps : productSales){
            Report report = Report.builder()
                    .date(this.date)
                    .userName(this.userName)
                    .category(ps.getCategory())
                    .pricePerUnit(ps.getPricePerUnit())
                    .productName(ps.getProductName())
                    .quantity(ps.getQuantity())
                    .total(ps.getTotal())
                    .vendorName(ps.getVendorName())
                    .build();
        }
        return reports;
    }

}
