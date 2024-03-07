package com.example.demo.payload.request.customer;

import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Role;

public record CustomerRegistrationRequest(
    String name,
    String email,
    String password,
    Integer age,
    Gender gender,
    Role role
) {
} 