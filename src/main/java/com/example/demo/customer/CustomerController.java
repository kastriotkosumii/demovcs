package com.example.demo.customer;

import com.example.demo.customer.Exception.EmailExistException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping(path = "api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerServiceImp customerServiceImp;

    public CustomerController(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerServiceImp.getAllCustomer();
    }

    @GetMapping("/{CustomerID}")
    public Optional<Customer> getCustomerById(@PathVariable("CustomerID") Long id) {
        return customerServiceImp.getCustomerById(id);
    }



    @DeleteMapping("/{CustomerID}")
    public void deleteCustomerById(@PathVariable("CustomerID") Long id){
        customerServiceImp.deleteCustomerById(id);
    }
    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) throws EmailExistException {
        return customerServiceImp.createCustomer(customer);
    }

    @PutMapping
    public Customer updateEmployee(@RequestBody Customer customer) throws Exception {
        return customerServiceImp.updateCustomer(customer);
    }
    

}
