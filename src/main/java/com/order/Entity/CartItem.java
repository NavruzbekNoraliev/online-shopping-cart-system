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
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Cart cart;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    private int quantity;
    private double subTotal;

    //calculate subTotal

}