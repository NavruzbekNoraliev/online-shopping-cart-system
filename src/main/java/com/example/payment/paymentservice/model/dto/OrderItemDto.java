package com.example.payment.paymentservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDto {
    private String productName;
    private String description;
    private String categoryName;
    private String vendorName;
    private int quantity;
    private double price;
    private Long vendorId;
}