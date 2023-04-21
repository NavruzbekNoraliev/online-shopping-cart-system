package com.order.Controller;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;
import com.order.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody CartItem cartItem,
                                           @RequestHeader("Authorization") String authorizationHeader){
        return ResponseEntity.ok(cartService.addItemToCart(cartItem, authorizationHeader));
    }
}
