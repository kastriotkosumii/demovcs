package com.example.demo.customer;

import java.util.List;

public record CustomerDto(Long id, 
                            String name, 
                            String email, 
                            Integer age, 
                            Gender gender, 
                            String username, 
                            Role role) {
    
}
