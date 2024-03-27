package com.example.demo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long cartId;
    private Double totalPrice = 0.0;
    private List<ProductCartDto> products = new ArrayList<>();
}
