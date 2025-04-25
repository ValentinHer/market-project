package com.valentin.market.web.controller;

import com.valentin.market.domain.dto.LoginDto;
import com.valentin.market.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginDto data) {
		return ResponseEntity.ok()
							 .header(HttpHeaders.AUTHORIZATION, this.authService.login(data))
							 .build();
	}
}
