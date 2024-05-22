package com.toj.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final static long expiredAt = 365 * 24 * 60 * 60 * 1000L; // 1년

    public String generateToken(String email) {
        return makeToken(email);
    }

    private String makeToken(String email) {
        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        // payload 에 들어갈 내용
        return Jwts.builder()
                .setIssuer("RUTI APP")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiredAt))
                .setSubject(email)
                .signWith(key)
                .compact();
    }

    public String getUserEmail(String token) {
        return getClaims(token);
    }

    private String getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
