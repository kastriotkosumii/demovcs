package com.example.demo.services;
import com.example.demo.customer.CustomerDAO;
import com.example.demo.payload.request.customer.CustomerRegistrationRequest;
import com.example.demo.payload.request.customer.CustomerUpdateRequest;
import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.Exception.DuplicateResourceException;
import com.example.demo.Exception.RequestValidationException;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO customerDAO;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CustomerDto> getAllCustomer(){
        return customerDAO.selectAllCustomers().stream().map(customerMapper::toDto).toList();
    }

    public CustomerDto getCustomer(Long id) throws ResourceNotFoundException{
        return customerDAO.selectCustomerById(id)
        .map(customerMapper::toDto)
        .orElseThrow(()-> new ResourceNotFoundException("Customer not found!"));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        // check if email exists
        String email = customerRegistrationRequest.email();
        if (customerDAO.existsCustomerWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }

        Customer customer = new Customer();
        customer.setName(customerRegistrationRequest.name());
        customer.setEmail(customerRegistrationRequest.email()); 
        customer.setPassword(passwordEncoder.encode(customerRegistrationRequest.password()));  
        customer.setAge(customerRegistrationRequest.age());    
        customer.setGender(customerRegistrationRequest.gender());    
        customer.setRole(customerRegistrationRequest.role());
        
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

    public Page<CustomerDto> getAllCustomerpag(Integer pageNumber, Integer pageSize, String sort){
        List<CustomerDto> allItems =  customerDAO.getCustomerPagination(pageNumber, pageSize, sort)
                                                .stream()
                                                .map(customerMapper::toDto)
                                                .toList();

        return new PageImpl<>(allItems);
    }

}
