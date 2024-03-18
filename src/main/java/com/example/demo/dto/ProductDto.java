package com.example.demo.dto;

public record ProductDto(
    String name,
    String description,
    Float weight,
    Float height,
    float width,
    int quantity,
    float price,
    Long customer_id
) {
   
}
