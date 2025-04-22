package com.valentin.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request.requestMatchers("/products/**")
														 .hasAnyRole("ADMIN", "CLIENT")
														 .requestMatchers("/clients/**")
														 .hasRole("ADMIN")
														 .requestMatchers("/categories/**")
														 .hasAnyRole("ADMIN", "CLIENT")
														 .requestMatchers("/purchases/**")
														 .hasAnyRole("ADMIN", "CLIENT")
														 .anyRequest()
														 .authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
							   .username("admin")
							   .password(passwordEncoder().encode("admin"))
							   .roles("ADMIN")
							   .build();

		UserDetails client = User.builder()
								 .username("client")
								 .password(passwordEncoder().encode("client"))
								 .roles("CLIENT")
								 .build();

		return new InMemoryUserDetailsManager(user, client);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
