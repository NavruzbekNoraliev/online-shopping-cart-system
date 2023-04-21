package com.order.DTO;

import com.order.Entity.Product;
import jakarta.persistence.*;

import java.util.List;

public class ShoppingCartDTO {
    private long id;
    private String emailid;
    private double totalPrice;
    private List<Product> products;
}
