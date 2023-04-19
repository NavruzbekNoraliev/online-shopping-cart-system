package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.Entity.ShoppingCart;

public interface ShoppingCartInterface {
    ShoppingCart addToCart(long product, long cartid);
     ShoppingCart createCart(String email);
     ShoppingCart deleteProduct(long cartid,long productid);
    ShoppingCart deleteAllProduct(long cartid);
}
