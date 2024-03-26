package com.example.demo.customer;

import com.example.demo.AbstractTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerServiceImpl extends AbstractTestcontainers {

    /*
    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerServiceImpl(
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
