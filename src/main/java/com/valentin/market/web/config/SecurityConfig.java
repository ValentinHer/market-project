package com.valentin.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/products/**").hasAnyRole("ADMIN", "CLIENT")
						.requestMatchers("/clients/all").hasAuthority("get_clients")
						.requestMatchers("/clients/**").hasAnyRole("ADMIN", "CLIENT")
						.requestMatchers("/categories/**").hasAnyRole("ADMIN", "CLIENT")
						.requestMatchers("/purchases/**").hasAnyRole("ADMIN", "CLIENT")
						.requestMatchers("/roles/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
