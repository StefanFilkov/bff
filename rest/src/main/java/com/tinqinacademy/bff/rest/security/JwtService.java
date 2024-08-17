package com.tinqinacademy.bff.rest.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${JWT_EXPIRATION_TIME}")
    private Long JWT_EXPIRATION_TIME;

    private final ObjectMapper objectMapper;

    public JwtService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public Map<String, String> getPayloadMap(String token) throws JsonProcessingException {
        String[] jwtParts = token.split("\\.");

        String payload = jwtParts[1];

        byte[] decodedBytes = Base64.getUrlDecoder().decode(payload);
        String decodedPayload = new String(decodedBytes);


        return objectMapper.readValue(decodedPayload, Map.class);
    }

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


        return objectMapper.convertValue(claims, Claims.class);

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //TOKEN VALIDITY
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, String userid) {
        final String userId = extractUserId(token);
        return (userId.equals(String.valueOf(userid)) && !isTokenExpired(token));
    }

    public String extractUserId(String token) {

        Claims claims = extractAllClaims(token);

        return (String) claims.get("id");
    }
}
