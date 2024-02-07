package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO CustomerDAO;

    public CustomerService(@Qualifier("jdbc") CustomerDAO customerDAO) {
        CustomerDAO = customerDAO;
    }

    public List<Customer> getAllCustomer(){
        return CustomerDAO.selectAllCustomers();
    }

}
