package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;
import com.example.demo.payload.request.Address.AddressRegistrtionRequest;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addAddress(AddressRegistrtionRequest addressRegistrtionRequest) {
        Customer customer = customerRepository.findById(addressRegistrtionRequest.customer_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));

        Address address = new Address();
        address.setCity(addressRegistrtionRequest.city());
        address.setCountry(addressRegistrtionRequest.country());
        address.setHouseNumber(addressRegistrtionRequest.houseNumber());
        address.setZipcode(addressRegistrtionRequest.zipcode());
        address.setCustomer(customer);

        addressRepository.save(address);
    }

    /*
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
    */
    
}
