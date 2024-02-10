package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Exception.ResourceNotFoundException;
import com.example.demo.customer.Exception.DuplicateResourceException;
import com.example.demo.customer.Exception.RequestValidationException;

import java.util.List;


@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jdbc") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomer(){
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Long id) throws ResourceNotFoundException{
        return customerDAO.selectCustomerById(id)
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
        if(!customerDAO.exsitsPersonWithId(id))
            throw new ResourceNotFoundException("Customer with id "+id+" not found!");
        customerDAO.deleteCustomer(id);
    }

    public void updateCustomer(Long customerId,
                               CustomerUpdateRequest updateRequest) {
        // TODO: for JPA use .getReferenceById(customerId) as it does does not bring object into memory and instead a reference
        Customer customer = getCustomer(customerId);

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
