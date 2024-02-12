package com.edu_manage.education_management.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SECRET_KEY = "3F4428472B4B6250655368566B5970337336763979244226452948404D635166";
    public String extractUsername(String token) { //this will return user email
        return extractClaim(token, Claims :: getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Object user){
        return generateToken(new HashMap<>(), user);
    }
    public String generateToken(HashMap<Object, Object> hashMap, Object user) {
        // Convert HashMap<Object, Object> to Map<String, Object>
        Map<String, Object> claimsMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            claimsMap.put(entry.getKey().toString(), entry.getValue());
        }
        Claims claims = Jwts.claims();
        claims.putAll(claimsMap);

    
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(((UserDetails) user).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}