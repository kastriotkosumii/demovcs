package com.example.demo.payload.request.product;

public record ProductRegistrationRequest(

String name,
String description,
Float weight,
Float height,
float width,
int quantity,
float price,
Long customer_id
) {
    
}
