package com.revature.katieskritters.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.katieskritters.dtos.responses.Principal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(Principal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userPrincipal.getId());
        claims.put("role", userPrincipal.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // public boolean validateToken(String token, Principal usePrincipal) {
    // String tokenUsername = extractUsername(token);
    // return (tokenUsername.equals(usePrincipal.getUsername()));
    // }

    // // public String extractUsername(String token) {
    // // return extractClaim(token, Claims::getSubject);
    // // }

    // // public <T> T extractClaim(String token, Function<Claims, T>
    // claimsResolver) {
    // // final Claims claims = extractAllClaims(token);
    // // return claimsResolver.apply(claims);
    // // }

    // public Claims extractAllClaims(String token) {
    // return
    // Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    // }

    // public String extraUserId(String token) {
    // return (String) extractAllClaims(token).get("id");
    // }

    // public String extraUserRole(String token) {
    // return (String) extractAllClaims(token).get("role");
    // }
}
