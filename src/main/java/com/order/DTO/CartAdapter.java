package com.order.DTO;

import com.order.Entity.Cart;

public class CartAdapter {
    //toDTO and fromDTO

    public static CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setEmail(cart.getEmail());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setProducts(cart.getProducts());
        return cartDTO;
    }

    public static Cart fromDTO(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setEmail(cartDTO.getEmail());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setProducts(cartDTO.getProducts());
        return cart;
    }
}
