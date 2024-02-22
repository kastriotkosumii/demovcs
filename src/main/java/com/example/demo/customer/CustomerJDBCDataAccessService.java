package com.example.demo.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Customer> selectCustomerById(Long id) {

        var sql = """
                Select * from customer where id = ?;
                """;

        return jdbcTemplate.query(sql, customerRowMapper, id)
                .stream()
                .findFirst();

    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                insert into customer (name, email, password, age, gender) values (?,?,?,?,?);
                """;

        int result = jdbcTemplate.update(
            sql,
            customer.getName(),
            customer.getEmail(),
            customer.getPassword(),
            customer.getAge(),
            customer.getGender()
        );

        System.out.println("Insert Customer result " + result);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        
        var sql = """
                select count(id) 
                from customer 
                where email = ?;
                """;
        
        Long count = jdbcTemplate.queryForObject(sql, Long.class, email);

        return count != null && count > 0;
    }

    @Override
    public void deleteCustomer(Long id) {
        var sql = """
                DELETE
                FROM customer
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql, id);
        System.out.println("deleteCustomerById result = " + result);
    }

    @Override
    public boolean existsCustomerWithId(Long id) {
        var sql = """
                SELECT count(id)
                FROM customer
                WHERE id = ?
                """;

        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);

        return count != null && count > 0;
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer.getName() != null) {
            String sql = "UPDATE customer SET name = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    customer.getName(),
                    customer.getId()
            );
            System.out.println("update customer name result = " + result);
        }
        if (customer.getAge() != null) {
            String sql = "UPDATE customer SET age = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    customer.getAge(),
                    customer.getId()
            );
            System.out.println("update customer age result = " + result);
        }
        if (customer.getEmail() != null) {
            String sql = "UPDATE customer SET email = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    customer.getEmail(),
                    customer.getId());
            System.out.println("update customer email result = " + result);
        }
    }

    @Override
    public Optional<Customer> selectUserByEmail(String email){

        var sql = """
                Select * from customer where email = ?;
                """;

        return jdbcTemplate.query(sql, customerRowMapper, email)
                .stream()
                .findFirst();
    }

}
