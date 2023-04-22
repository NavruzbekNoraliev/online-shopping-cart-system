package com.order.Service.Impl;

import com.order.Entity.*;
import com.order.ExeceptionHandling.shopping.InvalidQuantityException;
import com.order.ExeceptionHandling.shopping.ShoppingResourceNotFoundException;
import com.order.Repository.CartItemRepository;
import com.order.Repository.CartRepository;
import com.order.Repository.OrderItemRepository;
import com.order.Repository.OrderRepository;
import com.order.Service.CartService;
import com.order.Service.DTO.CustomerDTO;
import com.order.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService,
                           CartItemRepository cartItemRepository,
                           OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository){
        this.cartItemRepository=cartItemRepository;
        this.productService=productService;
        this.cartRepository=cartRepository;
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;

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
    public Cart removeItemFromCart(long cartItemId,long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
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
    public Cart updateCartItem(long cartItemId, CartItem cartItem, long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
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
    public Cart clearCart(long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
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

    @Override
    public Order checkoutCart(CustomerDTO customerDTO, Cart cart) {
        Order newOrder = Order.builder()
                .customerName(customerDTO.getFirstName() + " " + customerDTO.getLastName())
                .customerEmail(customerDTO.getCustomerEmail())
                .customerPhone(customerDTO.getCustomerPhone())
                .billingAddress(customerDTO.getBillingAddress())
                .shippingAddress(customerDTO.getShippingAddress())
                .orderDate(new Date())
                .status(OrderStatus.PENDING)
                .paymentMethod(PaymentMethod.MASTERCARD)
                .totalPrice(cart.getTotalPrice())
                .orderItems(new ArrayList<>())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem item : cart.getCartItems()){
            OrderItem orderItem = OrderItem.builder()
                    .productName(item.getProduct().getName())
                    .quantity(item.getQuantity())
                    .price(item.getProduct().getPrice())
                    .vendorName("vendor name")
                    .build();
            orderItems.add(orderItem);
        }

       orderItems.forEach(orderItem -> newOrder.getOrderItems().add(orderItemRepository.save(orderItem)));

        return orderRepository.save(newOrder);
    }
}
