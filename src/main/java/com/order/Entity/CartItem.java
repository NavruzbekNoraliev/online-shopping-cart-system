package com.order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cartId;
    private long productId;
    private String productName;
    private double price;
    private int quantity;
}
