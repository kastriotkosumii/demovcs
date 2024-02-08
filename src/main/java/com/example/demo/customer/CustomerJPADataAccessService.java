package com.example.demo.customer;

import org.springframework.stereotype.Service;

import com.example.demo.customer.Exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service("jpa")
public class CustomerJPADataAccessService implements CustomerDAO{

    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Long id){
        return customerRepository.findById(id);
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }
}
