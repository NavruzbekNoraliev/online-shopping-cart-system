package com.order.Service;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;
import com.order.Entity.Order;
import com.order.Service.DTO.CustomerDTO;

import java.util.List;

public interface CartService {
//    public Cart getCartById(Long id);

    public Cart addItemToCart(Long customerId, CartItem cartItem, String authorizationHeader);

//    public Cart saveCart(Cart cart);
//
//    public void deleteCartById(Long id);
//
//    public void deleteAllCarts();
//
//    public Cart updateCart(Cart cart);
//
//    public Cart getCartByUserId(Long id);

    Cart getCartByCustomerId(long l);

    Cart removeItemFromCart(long cartItemId, long customerId);

    Cart updateCartItem(long cartItemId, CartItem cartItem, long customerId);

    Cart clearCart(long customerId);

    Order checkoutCart(Long customerId, CustomerDTO customerDTO, List<Long> cartItemIds);
}
