package com.valentin.market.web.config;

import com.valentin.market.web.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		// Validar que sea un Header Authentication valido
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if(authHeader == null || !authHeader.startsWith("Bearer")){
			filterChain.doFilter(request, response);
			return;
		}

		// Validar que el JWT sea valido
		String jwt = authHeader.split(" ")[1].trim();

		if(!this.jwtUtil.isValid(jwt)){
			filterChain.doFilter(request, response);
			return;
		}

		// Cargar el usuario del UserDetailService
		String username = this.jwtUtil.getUsername(jwt);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		// Cargar el usuario en el contexto de seguridad
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword(), user.getAuthorities()
		);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
