package com.example.demo.services.impl;

import java.util.List;

import com.example.demo.Exception.RequestValidationException;
import com.example.demo.payload.request.product.ProductUpdateRequest;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.payload.request.product.ProductRegistrationRequest;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, ProductMapper productMapper){
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void addProduct(ProductRegistrationRequest request) {
        Customer customer = customerRepository.findById(request.customer_id())
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

        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                        .stream()
                        .map(productMapper::toDto)
                        .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                                .map(productMapper::toDto)
                                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        if(!productRepository.existsProductById(id))
            throw new ResourceNotFoundException("Product not found");
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long productId, ProductUpdateRequest updateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id [%s] not found".formatted(productId)
                ));

        boolean changes = false;

        if(updateRequest.name() != null && !updateRequest.name().equals(product.getName())){
            product.setName(updateRequest.name());
            changes = true;
        }

        if(updateRequest.description() != null && !updateRequest.description().equals(product.getDescription())){
            product.setDescription(updateRequest.description());
            changes = true;
        }

        if(updateRequest.weight() != null && !updateRequest.weight().equals(product.getWeight())){
            product.setWeight(updateRequest.weight());
            changes = true;
        }

        if(updateRequest.height() != null && !updateRequest.height().equals(product.getHeight())){
            product.setHeight(updateRequest.height());
            changes = true;
        }

        if(updateRequest.width() != null && !updateRequest.width().equals(product.getWeight())){
            product.setHeight(updateRequest.height());
            changes = true;
        }

        if(updateRequest.quantity() != null && !updateRequest.quantity().equals(product.getQuantity())){
            product.setQuantity(updateRequest.quantity());
            changes = true;
        }

        if(updateRequest.price() != null && !updateRequest.price().equals(product.getPrice())){
            product.setPrice(updateRequest.price());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data changes found");
        }

        productRepository.save(product);
    }

    /*
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

    public List<ProductDto> getAllProducts(){
        return productDAO.selectAllProducts()
                            .stream()
                            .map(productMapper::toDto)
                            .toList();
    }

    public ProductDto getProductById(Long id){
        return productDAO.selectProductById(id)
                            .map(productMapper::toDto)
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    public void deleteProductById(Long id){
        if(!productDAO.existsProductWithId(id))
            throw new ResourceNotFoundException("Product not found");
        productDAO.deleteProductById(id);
    }

    public void updateProduct(Long productId,
                              ProductUpdateRequest updateRequest){

        Product product = productDAO.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id [%s] not found".formatted(productId)
                ));

        boolean changes = false;

        if(updateRequest.name() != null && !updateRequest.name().equals(product.getName())){
            product.setName(updateRequest.name());
            changes = true;
        }

        if(updateRequest.description() != null && !updateRequest.description().equals(product.getDescription())){
            product.setDescription(updateRequest.description());
            changes = true;
        }

        if(updateRequest.weight() != null && !updateRequest.weight().equals(product.getWeight())){
            product.setWeight(updateRequest.weight());
            changes = true;
        }

        if(updateRequest.height() != null && !updateRequest.height().equals(product.getHeight())){
            product.setHeight(updateRequest.height());
            changes = true;
        }

        if(updateRequest.width() != null && !updateRequest.width().equals(product.getWeight())){
            product.setHeight(updateRequest.height());
            changes = true;
        }

        if(updateRequest.quantity() != null && !updateRequest.quantity().equals(product.getQuantity())){
            product.setQuantity(updateRequest.quantity());
            changes = true;
        }

        if(updateRequest.price() != null && !updateRequest.price().equals(product.getPrice())){
            product.setPrice(updateRequest.price());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data changes found");
        }

        productDAO.updateProduct(product);
    }
    */
}
