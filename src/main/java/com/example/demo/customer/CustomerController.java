package com.example.demo.customer;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.jwt.JWTUtil;

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

    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") Long id) throws ResourceNotFoundException{
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public ResponseEntity<?> regisCustomer(@RequestBody CustomerRegistrationRequest CustomerRegistrationRequest) {
        customerService.addCustomer(CustomerRegistrationRequest);
        String token = jwtUtil.issueToken(CustomerRegistrationRequest.email(), "ROLE_USER");
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
    
}
