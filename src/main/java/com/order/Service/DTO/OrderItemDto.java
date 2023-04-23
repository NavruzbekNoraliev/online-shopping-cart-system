package com.order.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private String productName;
    private String description;
    private String categoryName;
    private String vendorName;
    private int quantity;
    private double price;
    private Long vendorId;
    private String categoryId;
    private Long productId;
}
