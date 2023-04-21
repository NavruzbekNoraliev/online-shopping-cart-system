package com.order.Service;

import com.order.Entity.ShoppingCart;

public interface ShoppingCartInterface {
    ShoppingCart addToCart(long product, long cartid);
     ShoppingCart createCart(String email);
     ShoppingCart deleteProduct(long cartid,long productid);
    ShoppingCart deleteAllProduct(long cartid);
}
