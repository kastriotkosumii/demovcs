package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Address;
import com.example.demo.model.Product;

public interface AddressDAO {

    List<Address> selectAllAddresses();

    Optional<Address> selectAddressById(Long id);

    void deleteAddressById(Long id);

    void insertAddress(Address address);

    boolean existsAddressWithId(Long id);

    void updateAddress(Address address);
}
