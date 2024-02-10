package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Long id);
    Customer insertCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
    void deleteCustomer(Long id);
    boolean exsitsPersonWithId(Long id);
    void updateCustomer(Customer customer);
}
