package com.example.demo.auth;

import com.example.demo.customer.CustomerDto;

public record AuthResponse(
    CustomerDto customerDto,
    String token
) {
    
}
