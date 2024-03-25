package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.CustomerDto;
import com.example.demo.payload.request.customer.CustomerRegistrationRequest;
import com.example.demo.payload.request.customer.CustomerUpdateRequest;

public interface CustomerService {
    List<CustomerDto> getAllCustomer();
    CustomerDto getCustomer(Long id);
    void addCustomer(CustomerRegistrationRequest customerRegistrationRequest);
    void deleteCustomer(Long id);
    void updateCustomer(Long customerId, CustomerUpdateRequest updateRequest);
    Page<CustomerDto> getAllCustomerpag(Integer pageNumber, Integer pageSize, String sort);
}
