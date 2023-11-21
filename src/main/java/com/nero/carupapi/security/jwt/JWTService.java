package com.nero.carupapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    public static final String JWT_Secret = "fgmjwtapiresttest@5045asdbrtyhbrtybu";

    private Claims getALlClaimsFromToken(String token){
        final var key = Keys.hmacShaKeyFor(JWT_Secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.
                parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver){
        final var claims = this.getALlClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token){
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        final var expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public String getUsernameFromToken(String token){
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        final Map<String,Object> claims = Collections.singletonMap("ROLES", userDetails.getAuthorities().toString());
        return this.getToken(claims, userDetails.getUsername());
    }

    private String getToken(Map<String, Object> claims, String subject){
        final var key = Keys.hmacShaKeyFor(JWT_Secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000)))
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final var usernameFromUserDetails = userDetails.getUsername();
        final var usernameFromHWT = this.getUsernameFromToken(token);
        return (usernameFromUserDetails.equals(usernameFromHWT)) && !this.isTokenExpired(token);
    }
}
