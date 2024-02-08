package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Exception.ResourceNotFoundException;
import com.example.demo.customer.Exception.DuplicateResourceException;


import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomer(){
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Long id) throws ResourceNotFoundException{
        return customerDAO.selectCustomerById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Customer not foudn!"));
    }

    public Customer addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        
        String email = customerRegistrationRequest.email();
        if(customerDAO.existsPersonWithEmail(email)){
            throw new DuplicateResourceException("Email already taken!");
        }

        Customer customer = new Customer(
            customerRegistrationRequest.name(),
            customerRegistrationRequest.email(),
            customerRegistrationRequest.age()
        );

        return customerDAO.insertCustomer(customer);
    }

}
