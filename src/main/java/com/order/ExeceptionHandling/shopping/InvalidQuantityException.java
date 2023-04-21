package com.order.ExeceptionHandling.shopping;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException(String message){
        super(message);
    }
}
