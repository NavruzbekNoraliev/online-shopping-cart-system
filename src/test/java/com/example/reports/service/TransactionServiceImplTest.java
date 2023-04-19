package com.example.reports.service;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.ProductSales;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {
    @Autowired
    TransactionService transactionServiceImpl;

    @Test
    public void createTransactionTest(){
        ProductSales productSales = ProductSales.builder()
                .productId(1)
                .productName("Macbook")
                .category("Electronics")
                .pricePerUnit(1000)
                .quantity(2)
                .vendorId(1)
                .vendorName("apple")
                .total(2000)
                .build();

        TransactionDTO dto = TransactionDTO.builder()
                .date(new Date())
                .productSales(List.of(productSales))
                .userId(1)
                .userName("Arda")
                .build();
        transactionServiceImpl.createTransaction(dto);

    }

}