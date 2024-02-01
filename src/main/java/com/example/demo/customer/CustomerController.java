package com.example.demo.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "api/v1/customers")
@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService cs){
        customerService = cs;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCusotmers();
    }

}
