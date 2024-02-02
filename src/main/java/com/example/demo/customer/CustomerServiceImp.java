package com.example.demo.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCusotmers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id){
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Integer id){
        customerRepository.deleteById(id);
    }

    public Customer CreateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

}
