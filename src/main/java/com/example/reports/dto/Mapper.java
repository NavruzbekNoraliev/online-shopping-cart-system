package com.example.reports.dto;

import com.example.reports.model.ProductSales;
import com.example.reports.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .userName(dto.getUserName())
                .userId(dto.getUserId())
                .date(dto.getDate())
                .build();
        List<ProductSales> productSalesList = dto.getProductSales();
        productSalesList.forEach(
                (ps) -> {
                    ps.setTransaction(transaction);
                }
        );
        transaction.setProductSales(productSalesList);

        return transaction;
    }

    public Transaction transactionRequestToTransaction(TransactionRequest dto){
        Transaction transaction = Transaction.builder()
                .userName(dto.getCustomerName())
                .userId(dto.getCustomerId())
                .date(dto.getDate())
                .transactionAmount(dto.getTransactionAmount())
                .build();
        List<OrderItemDto> orderItems = dto.getOrderItem();
        List<ProductSales> productSalesList = new ArrayList<>();
        orderItems.forEach(orderItemDto -> {
            ProductSales productSales = ProductSales.builder()
                    .transaction(transaction)
                    .category(orderItemDto.getCategoryName())
                    .vendorName(orderItemDto.getVendorName())
                    .quantity(orderItemDto.getQuantity())
                    .productId(orderItemDto.getProductId())
                    .productName(orderItemDto.getProductName())
                    .vendorId(orderItemDto.getVendorId())
                    .pricePerUnit(orderItemDto.getPrice())
                    .description(orderItemDto.getDescription())
                    .total(orderItemDto.getQuantity() * orderItemDto.getPrice())
                    .build();
            productSalesList.add(productSales);
        });
        transaction.setProductSales(productSalesList);

        return transaction;
    }

}
