package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;

@Component
public class ProductMapper implements Mapper<ProductDto, Product> {

    @Override
    public ProductDto toDto(Product entity) {
        return new ProductDto(
            entity.getName(),
            entity.getDescription(),
            entity.getWeight(),
            entity.getHeight(),
            entity.getWidth(),
            entity.getQuantity(),
            entity.getPrice(),
            entity.getCustomer().getId());
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setWeight(dto.weight());
        product.setHeight(dto.height());
        product.setWeight(dto.width());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        return product;
    }
    
}
