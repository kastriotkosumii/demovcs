package com.example.demo.customer;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Long id);
    void insertCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
    void deleteCustomer(Long id);
    boolean existsCustomerWithId(Long id);
    void updateCustomer(Customer customer);
    Optional<Customer> selectUserByEmail(String email);
}
