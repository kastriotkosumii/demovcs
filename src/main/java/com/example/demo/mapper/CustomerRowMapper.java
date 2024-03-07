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
        return new Customer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getInt("age"),
                Gender.valueOf(rs.getString("gender")),
                rs.getString("password"),
                Role.valueOf(rs.getString("role"))
        );
    }
}
