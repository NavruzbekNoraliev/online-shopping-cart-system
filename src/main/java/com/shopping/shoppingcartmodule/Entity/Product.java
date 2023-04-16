package com.shopping.shoppingcartmodule.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private double price;
    private int quantity;
    private String name;


}
