package com.valentin.market.domain.service;

import com.valentin.market.domain.dto.LoginDto;
import com.valentin.market.web.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	public String login(LoginDto data) {
		UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		Authentication authentication = this.authenticationManager.authenticate(login);

		return this.jwtUtil.create(data.getEmail());
	}
}
