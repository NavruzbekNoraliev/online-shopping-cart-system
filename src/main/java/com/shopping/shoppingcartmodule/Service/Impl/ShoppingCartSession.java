package com.shopping.shoppingcartmodule.Service.Impl;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartSession {


    private Map<String, Integer> cart;

    public ShoppingCartSession() {
        cart = new HashMap<>();
    }

    public void addItem(String item, int quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
    }

    public void removeItem(String item, int quantity) {
        if (cart.containsKey(item)) {
            if (quantity >= cart.get(item)) {
                cart.remove(item);
            } else {
                cart.put(item, cart.get(item) - quantity);
            }
        }
    }
}
