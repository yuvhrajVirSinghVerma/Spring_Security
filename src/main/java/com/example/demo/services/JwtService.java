package com.example.demo.services;

import com.example.demo.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secret;

    private SecretKey getSecret(){
       return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        System.out.println(secret);
       return Jwts.builder()
                .subject(user.getId().toString())
                .claim("name",user.getName())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(getSecret())
                .compact();
    }

    public String generateRefreshToken(User user){
        System.out.println(secret);
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6))
                .signWith(getSecret())
                .compact();
    }

    public Long getUserId(String token) {
        System.out.println("jwtservice token "+token);
        //if token is expired then exception will be thrown
        Claims claims=Jwts.parser().verifyWith(getSecret())
                .build()
                .parseSignedClaims(token.trim())
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}
