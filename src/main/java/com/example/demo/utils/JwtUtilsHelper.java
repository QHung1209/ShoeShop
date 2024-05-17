package com.example.demo.utils;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;
 
    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }

    public Claims getIdFromJwtToken(String Jwt)
    {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Jwt = Jwt.substring(7);
        
        Claims claims =Jwts.parser().verifyWith(key).build().parseSignedClaims(Jwt).getPayload();
        return claims;
    }

    public boolean verifyToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser().verifyWith(key).build().parse(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
