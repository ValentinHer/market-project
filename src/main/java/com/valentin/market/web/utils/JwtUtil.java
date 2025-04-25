package com.valentin.market.web.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
	private final Algorithm algorithm;

	public JwtUtil(@Value("${jwt.secret-key}") String secretKey){
		this.algorithm = Algorithm.HMAC256(secretKey);
	}

	public String create(String username) {
		return JWT.create()
				  .withSubject(username)
				  .withIssuer("valentin")
				  .withIssuedAt(new Date())
				  .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
				  .sign(this.algorithm);
	}

	public Boolean isValid(String jwt) {
		try {
			JWT.require(this.algorithm)
			   .build()
			   .verify(jwt);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}

	public String getUsername(String jwt) {
		return JWT.require(this.algorithm)
				  .build()
				  .verify(jwt)
				  .getSubject();
	}
}
