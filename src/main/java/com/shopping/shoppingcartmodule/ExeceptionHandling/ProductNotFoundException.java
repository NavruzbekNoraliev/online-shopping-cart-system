package com.shopping.shoppingcartmodule.ExeceptionHandling;

public class ProductNotFoundException extends RuntimeException{
   public ProductNotFoundException(String message){
        super(message);
    }
}
