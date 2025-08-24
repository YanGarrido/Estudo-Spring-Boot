package com.example.meu_primeiro_springboot.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
  private static final String SECRET_KEY = "c3VhLXNlY3JldC1rZXktc3VwZXItc2VndXJhLWUtbG9uZ2EtYXF1aS0xMjM0NTY=";
  private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
  private static final long EXPIRATION_TIME = 86400000;

  public static String generateToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public static String extractEmail(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key).build()
        .parseClaimsJwt(token).getBody().getSubject();
  }

  public static boolean validateToken(String token){
    try{
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
      return true;
    }
    catch (JwtException e){
      return false;
    }
  }
}
