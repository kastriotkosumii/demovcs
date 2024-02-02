package com.example.demo.customer;

import com.example.demo.customer.Exception.CustomerNotFoundException;
import com.example.demo.customer.Exception.EmailExistException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(Integer id);

    void deleteCustomerById(Integer id);

    Customer createCustomer(Customer customer) throws EmailExistException;

    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

}
