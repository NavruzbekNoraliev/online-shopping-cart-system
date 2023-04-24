package com.example.reports.service;

import com.example.reports.dto.Mapper;
import com.example.reports.dto.TransactionDTO;
import com.example.reports.model.ProductSales;
import com.example.reports.model.Transaction;
import com.example.reports.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {
    @Autowired
    TransactionService transactionServiceImpl;
    @Autowired
    Mapper mapper;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void createTransactionTest(){
        Transaction transaction = Transaction.builder()
                .transactionId(1)
                .date(new Date())
                .userId(1)
                .userName("Arda")
                .transactionAmount(BigDecimal.valueOf(2900))
                .build();

        ProductSales productSales = ProductSales.builder()
                .transaction(transaction)
                .productId(1)
                .productName("Macbook")
                .category("Electronics")
                .pricePerUnit(1000)
                .quantity(2)
                .vendorId(1)
                .vendorName("Apple")
                .total(2000)
                .build();
        ProductSales productSales2 = ProductSales.builder()
                .transaction(transaction)
                .productId(2)
                .productName("Iphone")
                .category("Electronics")
                .pricePerUnit(900)
                .quantity(1)
                .vendorId(1)
                .vendorName("Apple")
                .total(900)
                .build();

        transaction.setProductSales(List.of(productSales, productSales2));
//        transactionRepository.save(transaction);
        transactionServiceImpl.createTransaction(transaction);

    }

    @Test
    public void createTransactionTest2(){
        Transaction transaction = Transaction.builder()
                .date(new Date())
                .userId(1)
                .userName("Arda")
                .transactionAmount(BigDecimal.valueOf(5700))
                .build();

        ProductSales productSales = ProductSales.builder()
                .transaction(transaction)
                .productId(3)
                .productName("Galaxy S20")
                .category("Electronics")
                .pricePerUnit(1000)
                .quantity(3)
                .vendorId(2)
                .vendorName("Samsung")
                .total(3000)
                .build();
        ProductSales productSales2 = ProductSales.builder()
                .transaction(transaction)
                .productId(2)
                .productName("Iphone")
                .category("Electronics")
                .pricePerUnit(900)
                .quantity(3)
                .vendorId(3)
                .vendorName("Best Buy")
                .total(2700)
                .build();

        transaction.setProductSales(List.of(productSales, productSales2));

        transactionServiceImpl.createTransaction(transaction);

    }

    @Test
    public void createTransactionTest3(){
        Transaction transaction = Transaction.builder()
                .date(new Date())
                .userId(2)
                .userName("Kaan")
                .transactionAmount(BigDecimal.valueOf(3000))
                .build();

        ProductSales productSales = ProductSales.builder()
                .transaction(transaction)
                .productId(1)
                .productName("Macbook")
                .category("Electronics")
                .pricePerUnit(1000)
                .quantity(2)
                .vendorId(1)
                .vendorName("Apple")
                .total(2000)
                .build();
        ProductSales productSales2 = ProductSales.builder()
                .transaction(transaction)
                .productId(3)
                .productName("Galaxy S20")
                .category("Electronics")
                .pricePerUnit(1000)
                .quantity(1)
                .vendorId(2)
                .vendorName("Samsung")
                .total(1000)
                .build();

        transaction.setProductSales(List.of(productSales, productSales2));

        transactionServiceImpl.createTransaction(transaction);

    }

}