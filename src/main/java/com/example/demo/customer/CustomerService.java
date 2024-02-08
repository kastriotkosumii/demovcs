package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Exception.ResourceNotFoundException;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO CustomerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        CustomerDAO = customerDAO;
    }

    public List<Customer> getAllCustomer(){
        return CustomerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Long id) throws ResourceNotFoundException{
        return CustomerDAO.selectCustomerById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Customer not foudn!"));
    }

}
