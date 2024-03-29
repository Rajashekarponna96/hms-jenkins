package com.spdx.hms.security;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${spdx.app.jwtSecret}")
  private String jwtSecret;

  @Value("${spdx.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(String  userId) {

   // UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject((userId))
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date((System.currentTimeMillis() ) + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
      throw new BadCredentialsException("INVALID_CREDENTIALS:Invalid JWT signature", e);
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
      throw new BadCredentialsException("INVALID_CREDENTIALS:Invalid JWT token", e);
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
      throw new BadCredentialsException("INVALID_CREDENTIALS: Token expired", e);
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
      throw new BadCredentialsException("INVALID_CREDENTIALS: ", e);
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
      throw new BadCredentialsException("INVALID_CREDENTIALS: ", e);
    }

   // return false;
  }
}
