package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Address;
import com.example.demo.model.Product;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Role;

public record CustomerDto(Long id, 
                            String name, 
                            String email, 
                            Integer age, 
                            Gender gender,
                            String username, 
                            Role role,
                            List<Product> product,
                            Address address) {
    
}
