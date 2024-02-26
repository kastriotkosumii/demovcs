package com.example.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerDto;
import com.example.demo.customer.mappers.CustomerMapper;
import com.example.demo.jwt.JWTUtil;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomerMapper customerMapper;

    public AuthService(AuthenticationManager authenticationManager,
                        JWTUtil jwtUtil,
                        CustomerMapper customerMapper){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerMapper = customerMapper;
    }

    public AuthResponse login(AuthRequest authRequest){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.username(), authRequest.password())
        );

        Customer principal = (Customer) authentication.getPrincipal();
        CustomerDto customerDto = customerMapper.toDto(principal);
        String token = jwtUtil.issueToken(customerDto.username(), customerDto.roles());
        
        return new AuthResponse(customerDto, token);
    }
}
