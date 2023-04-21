package com.order.ExeceptionHandling;


import com.order.ExeceptionHandling.shopping.InvalidQuantityException;
import com.order.ExeceptionHandling.shopping.ShoppingResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class ResponseEntityException extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            ShoppingResourceNotFoundException.class,
            InvalidQuantityException.class
    })
    public ResponseEntity<ExceptionResponse> ShoppingCartNotFoundException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), HttpStatus.BAD_REQUEST, ex.getStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<ExceptionResponse> InvalidQuantityException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), HttpStatus.BAD_REQUEST, ex.getStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
