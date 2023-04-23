package com.example.payment.paymentservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private long id;
    private String userId;
    private double totalPrice;
    private int quantity;
    private List<OrderItemDto> products =new ArrayList<>();

    public ShoppingCartDto(long id, String userId, double totalPrice, int quantity) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }
}