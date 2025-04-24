package com.valentin.market.domain.service;

import com.valentin.market.domain.Client;
import com.valentin.market.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAll() {
		return clientRepository.getAll();
	}

	public Optional<Client> getClient(String clientId) {
		return clientRepository.getClient(clientId);
	}

	public Client save(Client client) {
		String passwordEncoded = passwordEncoder.encode(client.getPassword());
		client.setPassword(passwordEncoded);

		return clientRepository.save(client);
	}

	public Boolean delete(String clientId) {
		return getClient(clientId).map(client -> {
									  clientRepository.delete(clientId);
									  return true;
								  })
								  .orElse(false);
	}
}
