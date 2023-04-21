package com.order.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private long id;
    private long cartId;
    private long productId;
    private String productName;
    private double price;
    private int quantity;

}
