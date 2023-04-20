package com.example.reports.dto;

import com.example.reports.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
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
    public Transaction DTOtoTransaction(TransactionDTO dto){
        Transaction transaction = Transaction.builder()
                .transactionId(dto.getTransactionId())
                .productSales(dto.getProductSales())
                .userName(dto.getUserName())
                .userId(dto.getUserId())
                .date(dto.getDate())
                .build();
        return transaction;
    }

}
