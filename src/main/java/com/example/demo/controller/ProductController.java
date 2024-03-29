package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.payload.request.product.ProductRegistrationRequest;
import com.example.demo.payload.request.product.ProductUpdateRequest;
import com.example.demo.services.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping(path = "api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public void registerProduct(@RequestBody ProductRegistrationRequest request) {
        productService.addProduct(request);
    }
    
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
    
    @PutMapping("{id}")
    public void putMethodName(@PathVariable Long id, @RequestBody ProductUpdateRequest entity) {
        productService.updateProduct(id, entity);;
    }
    
}
