package com.shopping.shoppingcartmodule.Entity;

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
    private int vendorId;
    private String color;
    private boolean available;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
   // private CategoryEnum category;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "product")
    private List<CustomerComment> commentList;


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
