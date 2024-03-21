package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.customer.AddressDAO;
import com.example.demo.customer.CustomerDAO;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;
import com.example.demo.payload.request.Address.AddressRegistrtionRequest;

@Service
public class AddressService {

    private final AddressDAO addressDAO;
    private final CustomerDAO customerDAO;

    public AddressService(AddressDAO addressDAO, @Qualifier("jpa") CustomerDAO customerDAO) {
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
    }

    public void addAddress(AddressRegistrtionRequest addressRegistrtionRequest){
        
        Customer customer = customerDAO.selectCustomerById(addressRegistrtionRequest.customer_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));

        Address address = new Address();
        address.setCity(addressRegistrtionRequest.city());
        address.setCountry(addressRegistrtionRequest.country());
        address.setHouseNumber(addressRegistrtionRequest.houseNumber());
        address.setZipcode(addressRegistrtionRequest.zipcode());
        address.setCustomer(customer);

        addressDAO.insertAddress(address);

    }
    
}
