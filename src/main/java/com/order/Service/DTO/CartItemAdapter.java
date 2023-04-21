//package com.order.Service.DTO;
//
//import com.order.Entity.CartItem;
//
//public class CartItemAdapter {
//    //toDTO and fromDTO
//
//    public static CartItemDTO toDTO(CartItem cartItem) {
//        CartItemDTO cartItemDTO = new CartItemDTO();
//        cartItemDTO.setId(cartItem.getId());
//        cartItemDTO.setCartId(cartItem.getCartId());
//        cartItemDTO.setProductId(cartItem.getProductId());
//        cartItemDTO.setProductName(cartItem.getProductName());
//        cartItemDTO.setPrice(cartItem.getPrice());
//        cartItemDTO.setQuantity(cartItem.getQuantity());
//        return cartItemDTO;
//    }
//
//    public static CartItem fromDTO(CartItemDTO cartItemDTO) {
//        CartItem cartItem = new CartItem();
//        cartItem.setId(cartItemDTO.getId());
//        cartItem.setCartId(cartItemDTO.getCartId());
//        cartItem.setProductId(cartItemDTO.getProductId());
//        cartItem.setProductName(cartItemDTO.getProductName());
//        cartItem.setPrice(cartItemDTO.getPrice());
//        cartItem.setQuantity(cartItemDTO.getQuantity());
//        return cartItem;
//    }
//}
