package com.order.Service.Impl;

import com.order.Entity.Category;
import com.order.Entity.CustomerComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Long vendorId;
    private String color;
    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
   // private CategoryEnum category;
    @OneToMany(fetch = FetchType.EAGER ,orphanRemoval = true ,mappedBy = "product")
    private List<CustomerComment> commentList;


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
