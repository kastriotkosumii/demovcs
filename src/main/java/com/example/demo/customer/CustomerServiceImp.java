package com.example.demo.customer;

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

    public Optional<Customer> getCustomerById(Integer id){
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Integer id){
        customerRepository.deleteById(id);
    }

    public Customer CreateCustomer(Customer customer) throws EmailExistException {

        if(customerRepository.existsCustomerByEmail(customer.getName())){
            System.out.println("This email already exist");
            throw new EmailExistException("Po Kari po");

        }
        return customerRepository.save(customer);
    }


}
