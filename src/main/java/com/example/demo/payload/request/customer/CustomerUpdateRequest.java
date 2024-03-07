package com.example.demo.payload.request.customer;

public record CustomerUpdateRequest(
    String name,
    String email,
    Integer age
) {}
