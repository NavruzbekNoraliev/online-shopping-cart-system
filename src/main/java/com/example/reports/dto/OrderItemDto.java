package com.example.reports.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private String productName;
    private String description;
    private String categoryName;
    private String vendorName;
    private int quantity;
    private double price;
    private Long vendorId;
    private Long productId;
}
