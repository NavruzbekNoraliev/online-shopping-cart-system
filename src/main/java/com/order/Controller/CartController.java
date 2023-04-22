package com.order.Controller;

import com.order.Entity.Cart;
import com.order.Entity.CartItem;
import com.order.Service.CartService;
import com.order.Service.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //TODO: get user id from token
        Long customerId = 1l;
        return ResponseEntity.ok(cartService.addItemToCart(customerId, cartItem, authorizationHeader));
    }

    @GetMapping
    public ResponseEntity<?> getCartByCustomerId(@RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1;
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<?> updateCartItem(@PathVariable("cartItemId") long cartItemId,
                                            @RequestBody CartItem cartItem,
                                            @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
        return ResponseEntity.ok(cartService.updateCartItem(cartItemId, cartItem, customerId));
    }

    @DeleteMapping
    public ResponseEntity<?> clearCart(@RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
        return ResponseEntity.ok(cartService.clearCart(customerId));

    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(
                                                @PathVariable("cartItemId") long cartItemId,
                                                @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        long customerId = 1l;
    return ResponseEntity.ok(cartService.removeItemFromCart(cartItemId, customerId));

    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutCart(@RequestBody List<Long> cartItemIds,
                                          @RequestHeader("Authorization") String authorizationHeader){
//        long customerId = cartService.getCustomerIdFromToken(authorizationHeader);
        CustomerDTO customerDTO = new CustomerDTO();
        Long customerId = 1l;
        return ResponseEntity.ok(cartService.checkoutCart(customerId,customerDTO, cartItemIds));
    }


}
