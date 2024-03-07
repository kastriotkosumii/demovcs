package com.example.demo.payload.response.auth;

import com.example.demo.dto.CustomerDto;

public record AuthResponse(
    CustomerDto customerDto,
    String token
) {
    
}
