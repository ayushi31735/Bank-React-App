package com.techlabs.bank.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.techlabs.bank.exception.UserApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
//	@Value("${app.jwt-secret}")
	private String jwtSecret = "2846f08a397dc5c54abad792f391cb4f6a42cb34ec0ad29ddfb1eb3eb33fad58";
	
//	@Value("{app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate = 604800000;
	
	public String generateToken(Authentication authentication)
	{
		System.out.println(authentication);
		String username = authentication.getName();
		
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.claim("role",authentication.getAuthorities())
				.signWith(key())
				.compact();
		return token;
	}
	
	private Key key()
	{
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUsername(String token)
	{
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		System.out.println("---------------->");
		System.out.println(claims.get("role"));
		String username = claims.getSubject();
//		getRoles(token);
		return username;
	}

//	public String getRoles(String token) {
//		
//		Claims claims = Jwts.parserBuilder()
//				.setSigningKey(key())
//				.build()
//				.parseClaimsJws(token)
//				.getBody();
//		
//		String username = claims.getSubject();
//		System.out.println("---------------->");
//		System.out.println(claims);
//		return username;
//		
//	}
	
	public boolean validateToken(String token)
	{
		try {
			Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parse(token);
			return true;
		}
		catch(MalformedJwtException ex) 
		{
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt token");
		}
		catch(ExpiredJwtException ex) 
		{
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt token");
		}
		catch(UnsupportedJwtException ex) 
		{
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Unsupported Jwt token");
		}
		catch(IllegalArgumentException ex) 
		{
			throw new UserApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
		catch(Exception e)
		{
			throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
		}
	}
	
}	
