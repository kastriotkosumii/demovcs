package com.example.demo.customer;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductDAO {
    List<Product> selectAllProducts();
    Product selectProductById(Long id);
    void deleteProduct(Long id);
    void insertProduct(Product product);
}
