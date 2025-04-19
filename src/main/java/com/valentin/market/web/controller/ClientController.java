package com.valentin.market.web.controller;

import com.valentin.market.domain.Client;
import com.valentin.market.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> getAll() {
		return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable("id") String clientId) {
		return clientService.getClient(clientId)
							.map(ResponseEntity::ok)
							.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Client> save(@RequestBody Client client) {
		return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") String clientId) {
		if (clientService.delete(clientId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
