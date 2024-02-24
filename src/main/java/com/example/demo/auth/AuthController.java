package com.example.demo.auth;

import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.openid.connect.sdk.AuthenticationResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);

        return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, authResponse.token())
                    .body(authResponse);
    }

}
