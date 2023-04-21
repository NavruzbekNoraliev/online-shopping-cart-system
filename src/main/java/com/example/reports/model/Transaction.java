package com.example.reports.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString(exclude = "productSales")
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
            cascade = CascadeType.ALL,
            mappedBy = "transaction",
            orphanRemoval = true
    )
    @JsonIgnoreProperties("transaction")
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
            reports.add(report);
        }
        return reports;
    }

}
