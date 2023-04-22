package com.adminmodule.exceptionResponse.userException;

public class HandleMissingServletRequestParameter extends RuntimeException{
    public HandleMissingServletRequestParameter(String message) {
        super(message);
    }
}
