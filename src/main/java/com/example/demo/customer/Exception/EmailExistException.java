package com.example.demo.customer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmailExistException extends Throwable {

    String message;
    public EmailExistException(String message) {
        this.message = message;
    }
}
