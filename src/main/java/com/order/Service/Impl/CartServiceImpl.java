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



    public Cart addItemToCart(Long customerId, CartItem cartItem, String authorizationHeader){
        Cart newCart;
        CartItem newCartItem;
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        Long productId = cartItem.getProduct().getId();
        Product product = productService.getProductById(productId);

        if(product == null){
            throw new ShoppingResourceNotFoundException("Product not found");
        }
        if(product.getQuantity() <= 0 || product.getQuantity() < cartItem.getQuantity()){
            throw new InvalidQuantityException("Not enough quantity");
        }

        newCartItem = cartItemRepository.save(cartItem);

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
                if (item.getProduct().getId() == product.getId()) {
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
                    removeItem(existingCart, item);
                    return cartRepository.save(existingCart);
                }
            }
            return existingCart;
        }else{
            Cart newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.setTotalPrice(0);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public Cart updateCartItem(long cartItemId, CartItem cartItem, long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        Product product;
        if(cart.isPresent()){
            Cart existingCart = cart.get();
            for(CartItem item : existingCart.getCartItems()){
                if(item.getId() == cartItemId){
                    product = productService.getProductById(cartItem.getProduct().getId());
                    if(product == null){
                        throw new ShoppingResourceNotFoundException("Product not found");
                    }
                    if(product.getQuantity() <= 0 || product.getQuantity() < cartItem.getQuantity()){
                        throw new InvalidQuantityException("Not enough quantity");
                    }
                    if(item.getQuantity()>cartItem.getQuantity()){
                        product.setQuantity(product.getQuantity() + (item.getQuantity() - cartItem.getQuantity()));
                        productService.updateProduct(product.getId(), product);
                    }else if(item.getQuantity()<cartItem.getQuantity()){
                        product.setQuantity(product.getQuantity() - (cartItem.getQuantity() - item.getQuantity()));
                        productService.updateProduct(product.getId(), product);
                    }else{
                        product.setQuantity(product.getQuantity() + item.getQuantity());
                        productService.updateProduct(product.getId(), product);
                    }
                    item.setQuantity(cartItem.getQuantity());
                    item.setSubTotal(item.calculateSubTotal());
                    cartItemRepository.save(item);
                    existingCart.setTotalPrice(existingCart.getTotalPrice() + cartItem.calculateSubTotal());
                    return cartRepository.save(existingCart);
                    }
                }
                    return existingCart;
        }else{
            Cart newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.setTotalPrice(0);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public Cart clearCart(long customerId) {
        Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
        if(cart.isPresent()){
            Cart existingCart = cart.get();
            for(CartItem item : existingCart.getCartItems()){
                removeItem(existingCart, item);
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

    private void removeItem(Cart existingCart, CartItem item) {
        Product product;
        product = productService.getProductById(item.getProduct().getId());
        if(product != null){
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productService.updateProduct(product.getId(), product);
        }
        existingCart.getCartItems().remove(item);
        cartItemRepository.delete(item);
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
