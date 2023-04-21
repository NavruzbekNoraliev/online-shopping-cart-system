package com.shopping.shoppingcartmodule.ExeceptionHandling;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException(String message){
        super(message);
    }
}
