package com.order.Service;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;

public interface CartService {
    public Cart getCartById(Long id);

    public Cart addItemToCart(CartItem cartItem, String authorizationHeader);

    public Cart saveCart(Cart cart);

    public void deleteCartById(Long id);

    public void deleteAllCarts();

    public Cart updateCart(Cart cart);

    public Cart getCartByUserId(Long id);

    Cart getCartByCustomerId(long l);
}
