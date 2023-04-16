package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;

public interface ShoppingCartInterface {
    ShoppingCart addToCart(Product product, long cartid);
     ShoppingCart createCart(String email);
     ShoppingCart deleteProduct(long cartid,long productid);
}
