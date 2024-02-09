package com.example.crm.manager;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtManager {

    @Value("${security.jwt.expiration-time}")
    private static long jwtExpiration;
    
    public static String generateJwt(String token){
        Instant now = Instant.now();
        Date date = Date.from(now);
        Date expirationDate = new Date(date.getTime() + jwtExpiration);

        return Jwts.builder()
                        .subject(token)
                        .issuedAt(date)
                        .expiration(expirationDate)
                        .signWith(secretKey())
                        .compact();
    }

    public static String readJwt(String jwt) throws JwtException {
        try {
            Claims claims = Jwts.parser()
                        .verifyWith(secretKey())
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
            return claims.getSubject();
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("JwT is malformed");
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtException(null, null, "Expired JWT");
        } catch (JwtException ex) {
            throw new JwtException("Problem in JWT");
        }
    }

    private static SecretKey secretKey(){
        String secretString = "wzUpGa9k4LTV3QHuY8qVrt6wOENkvdes5vLHVc1ex6581IiQ";
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }
}
