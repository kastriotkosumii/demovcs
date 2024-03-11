package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;


    private String picture;
}
