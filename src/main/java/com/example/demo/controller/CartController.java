package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.payload.request.cart.CartRegistrationRequest;
import com.example.demo.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartDto addProductToCart(@RequestBody CartRegistrationRequest cartRegistrationRequest){
        return cartService.addProductToCart(cartRegistrationRequest);
    }
}
