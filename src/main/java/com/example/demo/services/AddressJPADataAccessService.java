package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.customer.AddressDAO;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressJPADataAccessService implements AddressDAO {

    private final AddressRepository addressRepository;

    public AddressJPADataAccessService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void insertAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> selectAllAddresses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllAddresses'");
    }

    @Override
    public Optional<Address> selectAddressById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAddressById'");
    }

    @Override
    public void deleteAddressById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAddressById'");
    }

    @Override
    public boolean existsAddressWithId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsAddressWithId'");
    }

    @Override
    public void updateAddress(Address product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAddress'");
    }

    
}
