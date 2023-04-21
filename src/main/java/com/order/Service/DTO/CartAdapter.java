package com.order.Service.DTO;

import com.order.Entity.Cart;

public class CartAdapter {
    //toDTO and fromDTO

    public static CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        return cartDTO;
    }

    public static Cart fromDTO(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        return cart;
    }
}
