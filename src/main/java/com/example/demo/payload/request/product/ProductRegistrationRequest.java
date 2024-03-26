package com.example.demo.payload.request.product;

public record ProductRegistrationRequest(

String name,
String description,
Float weight,
Float height,
float width,
int quantity,
float price,

double discount,

double specialPrice,

Long customer_id
) {
    
}
