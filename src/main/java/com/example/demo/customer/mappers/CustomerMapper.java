package com.example.demo.customer.mappers;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerDto;

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
                customer.getRole()
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
