package com.valentin.market.persistence;

import com.valentin.market.domain.Client;
import com.valentin.market.domain.repository.ClientRepository;
import com.valentin.market.persistence.crud.ClienteCrudRepository;
import com.valentin.market.persistence.entity.Cliente;
import com.valentin.market.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

	@Autowired
	ClienteCrudRepository clienteCrudRepository;

	@Autowired
	ClientMapper mapper;

	@Override
	public List<Client> getAll() {
		return mapper.toClients((List<Cliente>) clienteCrudRepository.findAll());
	}

	@Override
	public Optional<Client> getClient(String clientId) {
		return clienteCrudRepository.findById(clientId).map(cliente -> mapper.toClient(cliente));
	}

	@Override
	public Client save(Client client) {
		Cliente cliente = mapper.toCliente(client);

		return mapper.toClient(clienteCrudRepository.save(cliente));
	}

	@Override
	public void delete(String clientId) {
		clienteCrudRepository.deleteById(clientId);
	}
}
