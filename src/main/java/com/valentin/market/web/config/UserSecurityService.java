package com.valentin.market.web.config;

import com.valentin.market.domain.Client;
import com.valentin.market.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
	private final ClientRepository clientRepository;

	@Autowired
	public UserSecurityService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = clientRepository.getByEmail(username)
										.orElseThrow(() -> new UsernameNotFoundException("Email " + username + "Not Found"));

		List<String> roleList = new ArrayList<>();
		roleList.add(client.getRole().getName());
		String[] roles = roleList.toArray(new String[0]);

		return User.builder()
				   .username(client.getEmail())
				   .password(client.getPassword())
				   .authorities(this.grantedAuthorities(roles))
				   .disabled(client.getDisabled())
				   .build();
	}

	private String[] getAuthorities(String role){
		if ("ADMIN".equals(role)){
			return new String[] {"get_clients"};
		}

		return new String[]{};
	}

	private List<GrantedAuthority> grantedAuthorities(String[] roles){
		List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

		for (String role: roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

			for (String authority: this.getAuthorities(role)){
				authorities.add(new SimpleGrantedAuthority(authority));
			}
		}
		
		return authorities;
	}
}
