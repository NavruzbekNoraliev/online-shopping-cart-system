package com.order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int vendorId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
   // private CategoryEnum category;

}
