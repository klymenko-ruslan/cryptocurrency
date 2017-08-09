package com.outsourcebooster.cryptocurrency.web.config.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;

@Component
open class JwtTokenUtil(@Value("\${jwt.secret}") private val secret: String,
                        @Value("\${jwt.expiration}") private val expiration: Long) {

    val CLAIM_KEY_SUBJECT = "sub";


    fun getUsernameFromToken(token: String): String? {
        try {
            val claims = getClaimsFromToken(token);
            return claims?.getSubject() ?: null
        } catch (e: Exception) {
            return null
        }
    }

    fun getExpirationDateFromToken(token: String): Date? {
        try {
            val claims = getClaimsFromToken(token);
            return claims?.getExpiration() ?: null
        } catch (e: Exception) {
            return null
        }
    }

    fun getClaimsFromToken(token: String): Claims? {
        try {
            return Jwts.parser()
                       .setSigningKey(secret)
                       .parseClaimsJws(token)
                       .getBody()
        } catch (e: Exception) {
            return null
        }
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration?.before( Date()) ?: true
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>();
        claims.put(CLAIM_KEY_SUBJECT, userDetails.getUsername());
        return generateTokenForClaims(claims);
    }

    fun generateTokenForClaims(claims: HashMap<String, Any>): String {
        val expirationDate = Date(System.currentTimeMillis() + expiration * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
