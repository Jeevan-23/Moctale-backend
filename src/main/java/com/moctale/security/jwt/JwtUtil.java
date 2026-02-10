package com.moctale.security.jwt;

import java.security.Signature;
import java.util.Date;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.moctale.security.user.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET = "a-very-long-random-secret-key-at-least-32-bytes";
	private static final long EXPIRATION = 24 * 60 * 60 * 1000;
	
	public String generateToken(CustomUserDetails user) {
		return Jwts.builder()
				.setSubject(user.getId().toString())
				.claim("role", user.getAuthorities().iterator().next().getAuthority())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Claims parseToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
