package com.nisum.ejerciciojavaspringboot.security;


import com.nisum.ejerciciojavaspringboot.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generarToken(String email) {

        Date now = new Date();

        Date expirationDate =
                new Date(now.getTime()
                        + jwtProperties.getExpiration());

        Key key = Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes());

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
}
