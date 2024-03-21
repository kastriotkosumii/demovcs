package com.example.demo.mapper;

import com.example.demo.model.Customer;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.enums.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer =  new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setAge(rs.getInt("age"));
        customer.setGender(Gender.valueOf(rs.getString("gender")));
        customer.setPassword(rs.getString("password"));
        customer.setRole(Role.valueOf(rs.getString("role")));

        return customer;
        
    }
}
