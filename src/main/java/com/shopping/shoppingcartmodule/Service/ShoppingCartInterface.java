package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.DTO.ShoppingCartDTO;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;

public interface ShoppingCartInterface {
    ShoppingCartDTO addToCart(long product, long cartid, int quantity);
     ShoppingCart createCart(String email);
     ShoppingCart deleteProductFromCart(long cartid,long productid);
    ShoppingCart deleteAllProduct(long cartid);
    String checkout(long cartId);
}
