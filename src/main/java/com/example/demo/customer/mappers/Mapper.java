package com.example.demo.customer.mappers;

public interface Mapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}
