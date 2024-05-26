package com.toj.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final static long expiredAt = 365 * 24 * 60 * 60 * 1000L; // 1년

    public String generateToken(Long memberId) {
        return makeToken(memberId);
    }

    private String makeToken(Long memberId) {
        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        // payload 에 들어갈 내용
        return Jwts.builder()
                .setIssuer("RUTI APP")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiredAt))
                .setSubject(String.valueOf(memberId))
                .signWith(key)
                .compact();
    }

    public Long getMemberId(String token) {
        return Long.parseLong(getClaims(token));
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
