package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ProductDto;
import com.example.demo.payload.request.product.ProductRegistrationRequest;
import com.example.demo.payload.request.product.ProductUpdateRequest;

public interface ProductService {
    void addProduct(ProductRegistrationRequest request);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Long productId, ProductUpdateRequest updateRequest);
}
