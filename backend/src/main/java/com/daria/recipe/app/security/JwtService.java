package com.daria.recipe.app.security;

import com.daria.recipe.app.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    public String generateToken(String userName, Long userId, String email) {
        Map<String, Object> claims = new HashMap<>();
        if (userId != null) {
            claims.put("userId", userId);
        }
        if (email != null) {
            claims.put("email", email);
        }
        return createToken(claims, userName);
    }

    public boolean isValidToken(String token, String userName) {
        try {
            String extractedUserName = extractUserName(token);
            return (extractedUserName.equals(userName)) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public long getExpiresInSeconds() {
        return jwtProperties.getExpiration() / 1000;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(jwtProperties.getExpiration());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.toInstant().isBefore(Instant.now());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
