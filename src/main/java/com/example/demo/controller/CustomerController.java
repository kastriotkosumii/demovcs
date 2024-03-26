package com.example.demo.controller;

import com.example.demo.payload.request.customer.CustomerRegistrationRequest;
import com.example.demo.payload.request.customer.CustomerUpdateRequest;
import com.example.demo.services.CustomerService;
import com.example.demo.dto.CustomerDto;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Exception.jwt.JWTUtil;

import java.util.List;


@RequestMapping(path = "api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final JWTUtil jwtUtil;

    public CustomerController(CustomerService customerService, JWTUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") Long id) throws ResourceNotFoundException{
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public ResponseEntity<?> regisCustomer(@RequestBody CustomerRegistrationRequest CustomerRegistrationRequest) {
        customerService.addCustomer(CustomerRegistrationRequest);
        String token = jwtUtil.issueToken(CustomerRegistrationRequest.email(), CustomerRegistrationRequest.role().name());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @DeleteMapping("{CustomerId}")
    public void deleteCustomer(@PathVariable("CustomerId") Long id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{CustomerId}")
    public void updateCustomer(@PathVariable("CustomerId") Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest){
        customerService.updateCustomer(id, customerUpdateRequest);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}/{sort}")
    public List<CustomerDto> getEmployees(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String sort) {
        Page<CustomerDto> data = customerService.getAllCustomerpag(pageNumber, pageSize, sort);
        return data.getContent();
    }
    
}
