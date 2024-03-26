package com.example.demo.services;

import com.example.demo.dto.CartDto;
import com.example.demo.payload.request.cart.CartRegistrationRequest;

public interface CartService {

    CartDto addProductToCart(CartRegistrationRequest cartRegistrationRequest);
}
