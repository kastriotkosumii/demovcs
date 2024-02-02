package com.example.demo.customer;

import org.hibernate.internal.util.type.PrimitiveWrapperHelper.IntegerDescriptor;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/{CustomerID}")
    public Optional<Customer> getCustomerById(@PathVariable("CustomerID") Integer id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{CusomerID}")
    public void deleteCustomerById(@PathVariable("CusomerID") Integer id){
        customerService.deleteCustomerById(id);
    }
    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.CreateCustomer(customer);
    }
    

}
