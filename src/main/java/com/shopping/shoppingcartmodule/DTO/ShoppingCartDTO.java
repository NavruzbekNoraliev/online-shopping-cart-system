package com.shopping.shoppingcartmodule.DTO;

import com.shopping.shoppingcartmodule.Entity.Product;
import jakarta.persistence.*;

import java.util.List;

public class ShoppingCartDTO {
    private long id;
    private String emailid;
    private double totalPrice;
    private List<Product> products;
}
