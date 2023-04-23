package com.order.Service.Impl;

import com.order.Entity.*;
import com.order.ExeceptionHandling.shopping.InvalidQuantityException;
import com.order.ExeceptionHandling.shopping.ShoppingResourceNotFoundException;
import com.order.Repository.CartItemRepository;
import com.order.Repository.CartRepository;
import com.order.Repository.OrderItemRepository;
import com.order.Repository.OrderRepository;
import com.order.Service.CartService;
import com.order.Service.DTO.*;
import com.order.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private GetVendorService getVendorService;
    private ProductDTOConverter productDTOConverter;



    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService,
                           CartItemRepository cartItemRepository,
                           OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository,
                           GetVendorService getVendorService,
                           ProductDTOConverter productDTOConverter ){
        this.cartItemRepository=cartItemRepository;
        this.productService=productService;
        this.cartRepository=cartRepository;
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.getVendorService = getVendorService;
        this.productDTOConverter = productDTOConverter;

    }

    @FeignClient(name = "vendor-service", url = "http://localhost:9090")
    public interface VendorFeignClient {
        @RequestMapping("/api/v1/vendor/{vendorId}")
        public VendorDTO getVendorById(@PathVariable("vendorId") Long vendorId);
    }

//    public VendorDTO getVendorById(Long vendorId){
//        return vendorFeignClient.getVendorById(vendorId);
//    }

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
        cartItem.setProduct(product);
        cartItem.setSubTotal(cartItem.getQuantity()*product.getPrice());
        newCartItem = cartItemRepository.save(cartItem);
        if(!cart.isPresent()){
            newCart = new Cart();
            newCart.setCustomerId(customerId);
            newCart.setCartItems(new ArrayList<>());
            newCart.getCartItems().add(newCartItem);
            newCart.setTotalPrice(cartItem.getQuantity()*product.getPrice());
            return cartRepository.save(newCart);
        }else if(cart.get().getCartItems().isEmpty()) {
            newCart = cart.get();
            newCart.setCartItems(new ArrayList<>());
            newCart.getCartItems().add(newCartItem);
            newCart.setTotalPrice(cartItem.getQuantity()*product.getPrice());
            return cartRepository.save(newCart);
        }else {
            newCart = cart.get();
            //check if this item already exists in the cart
            for (CartItem item : newCart.getCartItems()) {
                if (item.getProduct().getId() == product.getId()) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.setSubTotal((item.getQuantity()+cartItem.getQuantity())*product.getPrice());
                    cartItemRepository.save(item);
                    newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.getSubTotal());
                    return cartRepository.save(newCart);
                }
            }

            newCart.setTotalPrice(newCart.getTotalPrice() + cartItem.getQuantity()*product.getPrice());
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

                    item.setQuantity(cartItem.getQuantity());
                    item.setSubTotal(item.getQuantity()*product.getPrice());
                    cartItemRepository.save(item);
                    existingCart.setTotalPrice(existingCart.getTotalPrice() + item.getQuantity()*product.getPrice());
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
    public OrderDTO checkoutCart(Long customerId, CustomerDTO customerDTO, List<Long> cartItemIds)  {
        Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow(
                () -> new ShoppingResourceNotFoundException("Cart not found")
        );

        OrderDTO orderDTO = OrderDTO.builder()
                .customerId(customerId)
                .customerName(customerDTO.getFirstName() + " " + customerDTO.getLastName())
                .customerEmail(customerDTO.getCustomerEmail())
                .date(new Date())
                .orderItem(new ArrayList<>())
                .build();

        double addSubTotal = 0;
        OrderItemDto orderItemDto = new OrderItemDto();
        Product product;
        VendorDTO vendorDTO;
        for (CartItem item : cart.getCartItems()){
            if(cartItemIds.contains(item.getId())){
                product = productService.getProductById(item.getProduct().getId());
                if(product != null && product.getQuantity() > 0 && product.getQuantity() >= item.getQuantity()){
                    orderItemDto.setProductId(product.getId());
                    orderItemDto.setQuantity(item.getQuantity());
                    orderItemDto.setPrice(product.getPrice());
                    orderItemDto.setDescription(product.getDescription());
                    orderItemDto.setCategoryName(product.getCategory().getName());
                    orderItemDto.setProductName(product.getName());
                    orderItemDto.setVendorId(product.getVendorId());
                    try{
                        vendorDTO = getVendorService.getById(product.getVendorId().toString());
                    }catch (Exception e){
                        throw new ShoppingResourceNotFoundException(e.getMessage());
                    }
                    orderItemDto.setVendorName(vendorDTO.getName());
                    orderDTO.getOrderItem().add(orderItemDto);
                    addSubTotal += (item.getQuantity()*product.getPrice());
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productService.updateProduct(product.getId(),productDTOConverter.toDTO(product));
                    cart.setTotalPrice(cart.getTotalPrice() - item.getSubTotal());
                    cart.getCartItems().remove(item);
                    cartItemRepository.delete(item);
                    orderDTO.getOrderItem().add(orderItemDto);
                }
            }

        }
        orderDTO.setTransactionAmount(addSubTotal);


        return orderDTO;


    }
}
