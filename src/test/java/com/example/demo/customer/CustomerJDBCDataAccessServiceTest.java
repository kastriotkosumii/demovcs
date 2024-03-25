package com.example.demo.customer;

import com.example.demo.AbstractTestcontainers;
import com.example.demo.mapper.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {

    /*
    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        // Given
        Long id = 0L;

        // When
        var actual = underTest.selectCustomerById(id);

        // Then
        assertThat(actual).isEmpty();
    }
    */


}
