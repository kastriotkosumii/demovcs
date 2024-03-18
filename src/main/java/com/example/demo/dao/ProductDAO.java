package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Product;

public interface ProductDAO {
    List<Product> selectAllProducts();
    Optional<Product> selectProductById(Long id);
    void deleteProductById(Long id);
    void insertProduct(Product product);
    boolean existsProductWithId(Long id);
    void updateProduct(Product product);
}
