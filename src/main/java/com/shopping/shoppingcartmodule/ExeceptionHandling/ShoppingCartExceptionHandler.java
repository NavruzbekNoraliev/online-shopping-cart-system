package com.shopping.shoppingcartmodule.ExeceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShoppingCartExceptionHandler {

    @ExceptionHandler(value = { ShoppingCartNotFoundException.class })
    public ResponseEntity<Object> handleShoppingCartNotFoundException(ShoppingCartNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ProductNotFoundException.class })
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InvalidQuantityException.class })
    public ResponseEntity<Object> handleInvalidQuantityException(InvalidQuantityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = { InsufficientStockException.class })
//    public ResponseEntity<Object> handleInsufficientStockException(InsufficientStockException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }


}





