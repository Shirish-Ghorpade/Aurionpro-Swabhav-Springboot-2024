package com.aurionpro.model.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.aurionpro.model.exceptions.UserApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	public String generateToken(Authentication authentication) {
		String userName = authentication.getName();
		
		Date currentDate = new Date();
		
		Date expirationDate = new Date(currentDate.getTime()+jwtExpirationDate);
		
		String token = Jwts.builder().claims().subject(userName).issuedAt(currentDate).expiration(expirationDate).and().signWith(key())
				.claim("role", authentication.getAuthorities()).compact();
		return token;
	}

	private SecretKey key(){
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(key()).build().parse(token);
			return true;
		} catch (MalformedJwtException ex) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		} catch (Exception e) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
		}
	}

	public String getUsername(String token) {
		Claims claims = Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();

		String username = claims.getSubject();

		return username;
	}

}
