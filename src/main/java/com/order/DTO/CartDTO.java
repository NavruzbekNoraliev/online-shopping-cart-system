package com.order.DTO;

import com.order.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private long id;
    private String email;
    private double totalPrice;
    private List<Product> products;

}
