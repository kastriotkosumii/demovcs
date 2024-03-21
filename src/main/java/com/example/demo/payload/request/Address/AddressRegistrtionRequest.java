package com.example.demo.payload.request.Address;

public record AddressRegistrtionRequest(
     Integer houseNumber,
     String city,
     String country,
     Float zipcode,
     Long customer_id
) {
    
}
