package com.dairodev.api_foro.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String SECRET = "40A0DDDC5B4574DEFAA3942DE9652C102A815A209CBDD8F5E334671D5686DE1B3AFFF000322EBA67D359349F0FD407DDE0C6A5754522347C0B1478CF4B38C722";

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(getExpirationDate(30))
                .signWith(generatedKey())
                .compact();
    }

    private SecretKey generatedKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    private Date getExpirationDate(long expirationTimeMinutes) {
        return Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(expirationTimeMinutes)));
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token)
                .getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generatedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(Date.from(Instant.now()));
    }
}
