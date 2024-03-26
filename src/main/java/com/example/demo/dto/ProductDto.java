package com.example.demo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto{

    private String name;
    private String description;
    private Float weight;
    private Float height;
    private float width;
    private int quantity;
    private float price;
    private Long customer_id;
   
}
