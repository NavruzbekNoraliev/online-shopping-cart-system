package com.order.Service;

import com.order.Service.DTO.ProductDTO;

import java.util.List;

public interface CartItem {
    //addItemToCart
    public CartItem addItemToCart(ProductDTO productDTO);

    //updateCartItemQuantity
    public CartItem updateCartItemQuantity(int cartItemId, ProductDTO productDTO);

    //removeItemFromCart
    public CartItem removeItemFromCart(int customerId, ProductDTO productDTO);

    //removeAllItemsFromCart
    public void removeAllItemsFromCart();

    //getCartItems
    public List<CartItem> getCartItems(int customerId);

    //checkout




}