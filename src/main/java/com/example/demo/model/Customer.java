package com.example.demo.model;

import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
public class Customer extends BaseEntity implements UserDetails  {

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
    @Column(name = "customer_id")
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

    @Column(
        nullable = false
    )
    
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(
            nullable = false
    )
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy ="customer")
    @Column(
            nullable = true
    )
    @JsonManagedReference
    private List<Product> products;

    @OneToOne(mappedBy = "customer")
    @JsonManagedReference
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
