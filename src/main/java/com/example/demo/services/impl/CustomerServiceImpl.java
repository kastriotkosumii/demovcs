package com.example.demo.services.impl;
import com.example.demo.model.Cart;
import com.example.demo.payload.request.customer.CustomerRegistrationRequest;
import com.example.demo.payload.request.customer.CustomerUpdateRequest;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.CustomerService;
import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.Exception.DuplicateResourceException;
import com.example.demo.Exception.RequestValidationException;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerRepository.findById(id)
                                .map(customerMapper::toDto)
                                .orElseThrow(()-> new ResourceNotFoundException("Customer not found!"));
    }

    @Override
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        // check if email exists
        String email = customerRegistrationRequest.email();

        if (customerRepository.existsCustomerByEmail(email)) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }

        Customer customer = new Customer();
        Cart cart = new Cart();

        customer.setCart(cart);
        customer.setName(customerRegistrationRequest.name());
        customer.setEmail(customerRegistrationRequest.email()); 
        customer.setPassword(passwordEncoder.encode(customerRegistrationRequest.password()));  
        customer.setAge(customerRegistrationRequest.age());    
        customer.setGender(customerRegistrationRequest.gender());    
        customer.setRole(customerRegistrationRequest.role());

        cart.setCustomer(customer);
        
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if(!customerRepository.existsCustomerById(id))
            throw new ResourceNotFoundException("Customer with id "+id+" not found!");
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(Long customerId, CustomerUpdateRequest updateRequest) {
        Customer customer =  customerRepository.findById(customerId)
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
            if (customerRepository.existsCustomerByEmail(updateRequest.email())) {
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

        customerRepository.save(customer);
    }

    @Override
    public Page<CustomerDto> getAllCustomerpag(Integer pageNumber, Integer pageSize, String sort) {
        List<CustomerDto> allItems =  getCustomerPagination(pageNumber, pageSize, sort)
                                                .stream()
                                                .map(customerMapper::toDto)
                                                .toList();

        return new PageImpl<>(allItems);
    }

    private Page<Customer> getCustomerPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
        // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
        // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return customerRepository.findAll(pageable);
    }

}
