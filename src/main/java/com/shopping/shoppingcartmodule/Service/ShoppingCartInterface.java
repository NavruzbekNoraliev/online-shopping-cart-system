package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.DTO.ShoppingCartDTO;

public interface ShoppingCartInterface {
    ShoppingCartDTO addToCart(long product, long cartid, int quantity);
     ShoppingCartDTO createCart(String email);
     ShoppingCartDTO deleteProductFromCart(long cartid,long productid);
    ShoppingCartDTO  deleteAllProduct(long cartid);
    String checkout(long cartId);
}
