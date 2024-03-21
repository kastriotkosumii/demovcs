package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.request.Address.AddressRegistrtionRequest;
import com.example.demo.services.AddressService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void registerAddress(@RequestBody AddressRegistrtionRequest addressRegistrtionRequest) {
        addressService.addAddress(addressRegistrtionRequest);
    }
    
    
}
