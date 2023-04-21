package com.order.ExeceptionHandling.shopping;

public class ShoppingResourceNotFoundException extends RuntimeException{
   public ShoppingResourceNotFoundException(String message){
       super(message);

    }
}
