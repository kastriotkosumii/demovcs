package com.example.demo.customer;

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
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") Long id) throws ResourceNotFoundException{
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

    @PutMapping("{CustomerId}")
    public void updateCustomer(@PathVariable("CustomerId") Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest){
        customerService.updateCustomer(id, customerUpdateRequest);
    }
    
}
