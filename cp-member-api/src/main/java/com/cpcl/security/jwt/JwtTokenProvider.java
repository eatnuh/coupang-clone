package com.cpcl.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtTokenProperties jwtTokenProperties;

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtTokenProperties.getExpiryMillisecond());

        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtTokenProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtTokenProperties.getSecretKey())
                .compact();
    }

    public String getSubjectFromJwtToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtTokenProperties.getSecretKey())
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtTokenProperties.getSecretKey()).parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException ex) {
            log.error("유효하지 않는 JWT Token : SignatureException");
        } catch (MalformedJwtException ex) {
            log.error("유효하지 않는 JWT Token : MalformedException");
        } catch (ExpiredJwtException ex) {
            log.error("유효하지 않는 JWT Token : ExpiredJwtException");
        } catch (UnsupportedJwtException ex) {
            log.error("유효하지 않는 JWT Token : UnsupportedJwtException");
        } catch (IllegalArgumentException ex) {
            log.error("유효하지 않는 JWT Token : IllegalArgumentException");
        }
        return false;
    }
}
