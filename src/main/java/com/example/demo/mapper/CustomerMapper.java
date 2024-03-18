package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.model.Customer;
import com.example.demo.dto.CustomerDto;

@Component
public class CustomerMapper implements Mapper<CustomerDto, Customer >{

    @Override
    public CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(), 
                customer.getEmail(),
                customer.getAge(), 
                customer.getGender(), 
                customer.getUsername(),
                customer.getRole(),
                customer.getProducts()
        );
    }

    @Override
    public Customer toEntity(CustomerDto customerDto){
        return new Customer(
                    customerDto.name(), 
                    customerDto.email(), 
                    "password",
                    customerDto.age(), 
                    customerDto.gender(),
                    customerDto.role()
        );
    }
    
}
