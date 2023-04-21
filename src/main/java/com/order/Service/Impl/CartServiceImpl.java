package com.order.Service.Impl;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;
import com.order.ExeceptionHandling.shopping.InvalidQuantityException;
import com.order.ExeceptionHandling.shopping.ShoppingResourceNotFoundException;
import com.order.Repository.CartRepository;
import com.order.Service.CartService;
import com.order.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService,
                           CartItemRepository cartItemRepository){
        this.cartItemRepository=cartItemRepository;
        this.productService=productService;
        this.cartRepository=cartRepository;
    }



    public Cart addItemToCart(CartItem cartItem, String authorizationHeader){
        //TODO: get user id from token
        Long customerId = 1l;
        Cart newCart;
        CartItem newCartItem;
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        Long productId = cartItem.getProduct().getId();
        if(cartItem.getProduct() == null
                || productService.getProductById(productId) == null){
            throw new ShoppingResourceNotFoundException("Product not found");
        }
        if(cartItem.getProduct().getQuantity() <= 0){
            throw new InvalidQuantityException("Not enough quantity");
        }
        if(cartItem.getQuantity() <= cartItem.getProduct().getQuantity()){
            newCartItem = cartItemRepository.save(cartItem);
        }else{
            throw new InvalidQuantityException("Not enough quantity");
        }

        if(!cart.isPresent()){
            newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.setCartItems(new ArrayList<>());
            newCart.getCartItems().add(newCartItem);
            newCart.setTotalPrice(cartItem.calculateSubTotal());
            return cartRepository.save(newCart);
        }else if(cart.get().getCartItems().isEmpty()) {
            newCart = cart.get();
            newCart.setCartItems(new ArrayList<>());
            newCart.getCartItems().add(newCartItem);
            newCart.setTotalPrice(cartItem.calculateSubTotal());
            return cartRepository.save(newCart);
        }else {
            newCart = cart.get();
            //check if this item already exists in the cart
            for (CartItem item : newCart.getCartItems()) {
                if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.setSubTotal(item.calculateSubTotal() + cartItem.calculateSubTotal());
                    cartItemRepository.save(item);
                    newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.calculateSubTotal());
                    return cartRepository.save(newCart);
                }
            }

            newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.calculateSubTotal());
            newCart.getCartItems().add(newCartItem);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public Cart getCartByCustomerId(long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        if(cart.isPresent()){
            return cart.get();
        }else{
           Cart newCart = new Cart();
           newCart.setCustomerId(customerId);
            newCart.setTotalPrice(0);
           return cartRepository.save(newCart);
        }
    }

    @Override
    public Cart removeItemFromCart(long cartId, long cartItemId,long customerId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart existingCart = cart.get();
            for(CartItem item : existingCart.getCartItems()){
                if(item.getId() == cartItemId){
                    existingCart.setTotalPrice(existingCart.getTotalPrice() - item.getSubTotal());
                    existingCart.getCartItems().remove(item);
                    cartItemRepository.delete(item);
                    return cartRepository.save(existingCart);
                }
            }
            return existingCart;
        }
        Cart newCart = new Cart();
        newCart.setCustomerId(customerId);
        newCart.setTotalPrice(0);
        return cartRepository.save(newCart);

    }

    @Override
    public Cart updateCartItem(long cartId, long cartItemId, CartItem cartItem, long customerId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart existingCart = cart.get();
            for(CartItem item : existingCart.getCartItems()){
                if(item.getId() == cartItemId){
                    if(cartItem.getQuantity() <= cartItem.getProduct().getQuantity()){
                        item.setQuantity(cartItem.getQuantity());
                        item.setSubTotal(item.calculateSubTotal());
                        cartItemRepository.save(item);
                        existingCart.setTotalPrice(existingCart.getTotalPrice() + cartItem.calculateSubTotal());
                        return cartRepository.save(existingCart);
                    }else{
                        throw new InvalidQuantityException("Not enough quantity");
                    }
                }else{
                    return existingCart;
                }
            }
        }
        Cart newCart = new Cart();
        newCart.setCustomerId(customerId);
        newCart.setTotalPrice(0);
        return cartRepository.save(newCart);
    }

    @Override
    public Cart clearCart(long cartId, long customerId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart existingCart = cart.get();
            for(CartItem item : existingCart.getCartItems()){
                existingCart.getCartItems().remove(item);
                cartItemRepository.delete(item);
            }
            existingCart.setTotalPrice(0);
            return cartRepository.save(existingCart);
        }else{
            Cart newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.setTotalPrice(0);
            return cartRepository.save(newCart);
        }
    }
}
