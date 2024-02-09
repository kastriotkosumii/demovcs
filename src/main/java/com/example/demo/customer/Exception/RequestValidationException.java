package com.example.demo.customer.Exception;

public class RequestValidationException extends RuntimeException {

    public RequestValidationException(String msg){
        super(msg);
    }
    
}
