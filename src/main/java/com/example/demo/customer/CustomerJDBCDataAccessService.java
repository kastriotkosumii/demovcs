package com.example.demo.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDAO {

    private final CustomerRowMapper customerRowMapper;
    private final JdbcTemplate  jdbcTemplate;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        var sql= """
                Select * from customer;
                """;

        return jdbcTemplate.query(sql, customerRowMapper);
    }
}
