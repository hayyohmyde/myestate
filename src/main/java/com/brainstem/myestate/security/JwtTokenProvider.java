package com.brainstem.myestate.security;

import com.brainstem.myestate.ResponseObject;
import com.brainstem.myestate.exception.ResourceNotFoundException;
import com.brainstem.myestate.payload.ResponseData;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    int jwtExpirationInMs;

    //Generate Token to set username
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    //Get user name from token
    public  String getUsernameFromJwtToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJwt(token)
                .getBody();
        return claims.getSubject();
    }

    //Validate token
    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch(SignatureException sex){
            throw new ResourceNotFoundException(sex.getMessage(), token, HttpStatus.BAD_REQUEST.value());
        }catch(MalformedJwtException mjex){
            throw new ResourceNotFoundException(mjex.getMessage(), token, HttpStatus.BAD_REQUEST.value());
        }catch(ExpiredJwtException ejex){
            throw new ResourceNotFoundException(ejex.getMessage(), token, HttpStatus.BAD_REQUEST.value());
        }catch(UnsupportedJwtException ujex){
            throw new ResourceNotFoundException(ujex.getMessage(), token, HttpStatus.BAD_REQUEST.value());
        }catch(IllegalArgumentException iaex){
            throw new ResourceNotFoundException(iaex.getMessage(), token, HttpStatus.BAD_REQUEST.value());
        }
    }
}
