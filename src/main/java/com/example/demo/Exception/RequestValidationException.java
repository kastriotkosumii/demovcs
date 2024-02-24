package com.example.demo.Exception;

public class RequestValidationException extends RuntimeException {

    public RequestValidationException(String msg){
        super(msg);
    }
    
}
