package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.customer.CustomerDAO;
import com.example.demo.customer.ProductDAO;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.payload.request.product.ProductRegistrationRequest;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final CustomerDAO customerDAO;

    public ProductService(ProductDAO productDAO, @Qualifier("jpa") CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
        this.productDAO = productDAO;
    }


    public void addProduct(ProductRegistrationRequest request){

        Customer customer = customerDAO.selectCustomerById(request.customer_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));

        Product product = new Product();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setWeight( request.weight());
        product.setHeight(request.height());
        product.setWidth( request.width());
        product.setQuantity(request.quantity());
        product.setPrice(request.price());
        product.setCustomer(customer);

        productDAO.insertProduct(product);
    }

    
}
