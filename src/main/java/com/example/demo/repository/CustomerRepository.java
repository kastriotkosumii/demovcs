package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Long id);
    Optional<Customer> findCustomerByEmail(String email);
}
