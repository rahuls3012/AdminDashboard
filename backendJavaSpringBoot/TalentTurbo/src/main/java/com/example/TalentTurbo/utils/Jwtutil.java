package com.example.TalentTurbo.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class Jwtutil {
    private final String SECRET="talenturbousermanagementsystemusingspringbootandreact";
    private final long EXPIRATION= 1000*60*60;
    private final Key secretKey= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public String jwtTokenGeneration(String mail){
        return Jwts.builder()
                .setSubject(mail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }
    public String extractMail(String token){
        return  Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateJWT(String token){
        try{
           extractMail(token);

            return true;

        }catch (JwtException exception){
            return false;
        }
    }

}
