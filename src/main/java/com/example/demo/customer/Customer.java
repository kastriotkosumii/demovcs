package com.example.demo.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email")
        }
)
public class Customer {

    @Id
    @SequenceGenerator(
        name = "customer_id_seq",
        sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer_id_seq"
    )
    private Long id;

    @Column(
        nullable = false
    )
    private String name;

    @Column(
        nullable = false
    )
    private String email;

    @Column(
        nullable = false
    )
    private Integer age;

}
