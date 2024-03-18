package com.example.demo.services;

import com.example.demo.dao.CustomerDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    
    private final CustomerDAO customerDao;

    public CustomerUserDetailsService(@Qualifier("jpa") CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerDao.selectUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email with "+ email +" not found!"));
    }

}
