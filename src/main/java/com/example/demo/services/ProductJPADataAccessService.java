package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.customer.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductJPADataAccessService implements ProductDAO{

    private final ProductRepository productRepository;

    @Override
    public List<Product> selectAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllProducts'");
    }

    @Override
    public Product selectProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectProductById'");
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }
    
}
