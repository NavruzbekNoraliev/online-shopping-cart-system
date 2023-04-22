package com.adminmodule.exceptionResponse;

import com.adminmodule.exceptionResponse.userException.HandleMissingServletRequestParameter;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Controller
@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            UserNotFoundException.class,
            UserBadRequestException.class,
            HandleMissingServletRequestParameter.class
    })
    public ResponseEntity<ExceptionResponse> UserNotFoundException(Exception e, WebRequest request){
       ExceptionResponse message = new ExceptionResponse(
               new Date(), HttpStatus.BAD_REQUEST, e.getMessage()
       );
       return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<ExceptionResponse> UserBadRequestException(Exception e, WebRequest request){
        ExceptionResponse message = new ExceptionResponse(
                new Date(), HttpStatus.BAD_REQUEST, e.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ExceptionResponse> HandleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

}

