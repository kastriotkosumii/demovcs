package com.example.demo.customer;

import com.example.demo.customer.Exception.CustomerNotFoundException;
import com.example.demo.customer.Exception.EmailExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

    public Customer createCustomer(Customer customer) throws EmailExistException {

        if(customerRepository.existsCustomerByEmail(customer.getName())){
            throw new EmailExistException("Email already exist.");

        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer c) throws CustomerNotFoundException {
        customerRepository.findById(c.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        return customerRepository.save(c);

    }

}
