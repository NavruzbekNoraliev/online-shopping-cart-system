package com.order.Service.DTO;

import com.order.Service.Impl.Product;

import java.util.List;

public class ShoppingCartDTO {
    private long id;
    private String emailid;
    private double totalPrice;
    private List<Product> products;
}
