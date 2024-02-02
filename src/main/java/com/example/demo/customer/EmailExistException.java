package com.example.demo.customer;

public class EmailExistException extends Throwable {

    String message;
    public EmailExistException(String message) {
        this.message = message;
    }
}
