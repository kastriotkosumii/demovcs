package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCusotmers();

    Optional<Customer> getCustomerById(Integer id);

    void deleteCustomerById(Integer id);

    Customer CreateCustomer(Customer customer) throws EmailExistException;

}
