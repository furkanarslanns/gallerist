package com.furkanarslan.gallerist.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public  static final String SECRET_KEY = "NURqYqwiEIOo+eR7PYIpW6o+JW9A984LnL0H1NmzvWU=";


    public String generateToken(UserDetails userDetails) {
       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(getSecretKey(),SignatureAlgorithm.HS256 )
                .compact();

    }

    public Claims getClaims(String token) {
       Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }
    public <T> T exportToken(String token, Function<Claims,T> claimsFunction) {
        Claims claims = getClaims(token);
        return claimsFunction.apply(claims);
    }
    public String getUsernameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }
    public boolean isTokenExpired(String token) {
        Date expiredDate=exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }
    public Key getSecretKey() {

        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(bytes);
    }

}
