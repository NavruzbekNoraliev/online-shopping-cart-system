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

    @GetMapping
    public ResponseEntity<?> getCartByCustomerId(@RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1;
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PutMapping("/{cartId}/item/{cartItemId}")
    public ResponseEntity<?> updateCartItem(@PathVariable("cartId") long cartId,
                                            @PathVariable("cartItemId") long cartItemId,
                                            @RequestBody CartItem cartItem,
                                            @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
        return ResponseEntity.ok(cartService.updateCartItem(cartId, cartItemId, cartItem, customerId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable("cartId") long cartId,
                                        @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
        return ResponseEntity.ok(cartService.clearCart(cartId, customerId));

    }

    @DeleteMapping("/{cartId}/item/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable("cartId") long cartId,
                                                @PathVariable("cartItemId") long cartItemId,
                                                @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
    return ResponseEntity.ok(cartService.removeItemFromCart(cartId, cartItemId, customerId));

    }


}
