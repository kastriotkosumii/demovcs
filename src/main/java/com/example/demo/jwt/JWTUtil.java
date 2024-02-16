package com.example.demo.jwt;

import java.time.Instant;

import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class JWTUtil {
    
    public String issueToken(String subject, Map<String, Object> claims){
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("https://demo-api.ks")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, DAYS)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

}
