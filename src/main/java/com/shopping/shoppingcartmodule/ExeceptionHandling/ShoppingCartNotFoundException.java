package com.shopping.shoppingcartmodule.ExeceptionHandling;

public class ShoppingCartNotFoundException extends RuntimeException{
   public ShoppingCartNotFoundException(String message){
       super(message);

    }
}
