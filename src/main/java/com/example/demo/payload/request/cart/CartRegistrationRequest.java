package com.example.demo.payload.request.cart;

public record CartRegistrationRequest(
        Long cartId,
        Long productId,
        Integer quantity
) {
}
