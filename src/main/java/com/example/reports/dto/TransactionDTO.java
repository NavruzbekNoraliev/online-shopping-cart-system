package com.example.reports.dto;

import com.example.reports.model.ProductSales;
import com.example.reports.model.Transaction;
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
public class TransactionDTO {

    private long transactionId;
    private List<ProductSales> productSales;
    private Date date;
    private long userId;
    private String userName;

    public TransactionDTO transactionToDto(Transaction transaction){
        TransactionDTO dto = TransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .productSales(transaction.getProductSales())
                .userName(transaction.getUserName())
                .userId(transaction.getUserId())
                .date(transaction.getDate())
                .build();
        return dto;
    }
}