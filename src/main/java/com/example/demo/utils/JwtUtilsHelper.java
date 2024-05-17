package com.example.demo.utils;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;
 
    public String generateToken(UserDTO user) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().claim("user", user).signWith(key).compact();
        return jws;
    }

    public Claims getIdFromJwtToken(String Jwt)
    {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Jwt = Jwt.substring(7);
        
        Claims claims =Jwts.parser().verifyWith(key).build().parseSignedClaims(Jwt).getPayload();
        return claims;
    }

}
