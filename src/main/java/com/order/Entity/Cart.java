package com.order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private double totalPrice;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Product> products;
}
