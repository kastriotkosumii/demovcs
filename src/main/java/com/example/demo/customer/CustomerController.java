package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.demo.customer.Exception.ResourceNotFoundException;

import java.util.List;




@RequestMapping(path = "api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long id) throws ResourceNotFoundException{
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public void regisCustomer(@RequestBody CustomerRegistrationRequest CustomerRegistrationRequest) {
        customerService.addCustomer(CustomerRegistrationRequest);
    }

    @DeleteMapping("{CustomerId}")
    public void deleteCustomer(@PathVariable("CustomerId") Long id){
        customerService.deleteCustomer(id);
    }
    
}
