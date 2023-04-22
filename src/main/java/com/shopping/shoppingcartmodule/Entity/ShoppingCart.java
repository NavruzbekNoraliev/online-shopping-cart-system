package com.shopping.shoppingcartmodule.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="cart")
public class ShoppingCart {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    private double totalPrice;
    private  int quantity;
    @ElementCollection
    private Map<Long,Integer> buketProduct=new HashMap<>();


}
