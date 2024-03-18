package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Long id);
    void insertCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
    void deleteCustomer(Long id);
    boolean existsCustomerWithId(Long id);
    void updateCustomer(Customer customer);
    Optional<Customer> selectUserByEmail(String email);
    Page<Customer> getCustomerPagination(Integer pageNumber, Integer pageSize, String sort);
}
