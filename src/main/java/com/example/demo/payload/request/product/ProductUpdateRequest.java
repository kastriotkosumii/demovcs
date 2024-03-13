package com.example.demo.payload.request.product;

public record ProductUpdateRequest(
        String name,
        String description,
        Float weight,
        Float height,
        Float width,
        Integer quantity,
        Float price

) {
}
