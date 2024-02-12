package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Exception.ResourceNotFoundException;
import com.example.demo.customer.mappers.CustomerMapper;
import com.example.demo.customer.Exception.DuplicateResourceException;
import com.example.demo.customer.Exception.RequestValidationException;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO customerDAO;
    private final CustomerMapper customerMapper;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO, CustomerMapper customerMapper) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> getAllCustomer(){
        return customerDAO.selectAllCustomers().stream().map(customerMapper::toDto).toList();
    }

    public CustomerDto getCustomer(Long id) throws ResourceNotFoundException{
        return customerDAO.selectCustomerById(id)
        .map(customerMapper::toDto)
        .orElseThrow(()-> new ResourceNotFoundException("Customer not foudn!"));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        // check if email exists
        String email = customerRegistrationRequest.email();
        if (customerDAO.existsCustomerWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }

        Customer customer = new Customer(
            customerRegistrationRequest.name(),
            customerRegistrationRequest.email(),
            customerRegistrationRequest.age()
        );

        customerDAO.insertCustomer(customer);
    }

    public void deleteCustomer(Long id) throws ResourceNotFoundException{
        if(!customerDAO.existsCustomerWithId(id))
            throw new ResourceNotFoundException("Customer with id "+id+" not found!");
        customerDAO.deleteCustomer(id);
    }

    public void updateCustomer(Long customerId,
                               CustomerUpdateRequest updateRequest) {
        
        Customer customer =  customerDAO.selectCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(customerId))
                );

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDAO.existsCustomerWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if (!changes) {
           throw new RequestValidationException("no data changes found");
        }

        customerDAO.updateCustomer(customer);
    }

}
