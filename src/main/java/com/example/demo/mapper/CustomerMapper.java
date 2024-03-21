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
                customer.getProducts(),
                customer.getAddress()
        );
    }

    @Override
    public Customer toEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.name());  
        customer.setEmail(customerDto.email());  
        customer.setPassword("password"); 
        customer.setAge(customerDto.age());  
        customer.setGender(customerDto.gender());
        customer.setRole(customerDto.role());

        return customer;
        
    }
    
}
