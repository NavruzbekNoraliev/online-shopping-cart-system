package com.order.Service.Impl;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;
import com.order.Repository.CartRepository;
import com.order.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository){
        this.cartItemRepository=cartItemRepository;
        this.cartRepository=cartRepository;
    }

    @Override
    public Cart getCartById(Long id) {
        return null;
    }

    public Cart addItemToCart(CartItem cartItem, String authorizationHeader){
        //TODO: get user id from token
        Long customerId = 1l;
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        CartItem newCartItem = cartItemRepository.save(cartItem);
        Cart newCart;
        if(!cart.isPresent()){
            newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.getCartItems().add(newCartItem);
            newCart.setTotalPrice(cartItem.getSubTotal());
        }else {
            newCart = cart.get();
            //check if this item already exists in the cart
            for(CartItem item: newCart.getCartItems()){
                if(item.getProduct().getId()==cartItem.getProduct().getId()){
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.setSubTotal(item.getSubTotal() + cartItem.getSubTotal());
                    cartItemRepository.save(item);
                    newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.getSubTotal());
                    return cartRepository.save(newCart);
                }
            }
            newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.getSubTotal());
            newCart.getCartItems().add(cartItemRepository.save(cartItem));
        }
        return cartRepository.save(newCart);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return null;
    }

    @Override
    public void deleteCartById(Long id) {

    }

    @Override
    public void deleteAllCarts() {

    }

    @Override
    public Cart updateCart(Cart cart) {
        return null;
    }

    @Override
    public Cart getCartByUserId(Long id) {
        return null;
    }
}
