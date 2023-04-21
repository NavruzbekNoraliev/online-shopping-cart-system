package com.order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="cart")
public class ShoppingCart {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String emailid;
//    private long productid;
//    private String productname;
    private double totalPrice;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Product> products;

}
