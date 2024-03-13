package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean existsProductWithId(Long id) {
        return productRepository.existsProductById(id);
    }
    
}
