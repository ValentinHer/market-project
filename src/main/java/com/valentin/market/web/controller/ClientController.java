package com.valentin.market.web.controller;

import com.valentin.market.domain.Client;
import com.valentin.market.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@GetMapping("/all")
	@Operation(description = "Get all clients")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Client>> getAll() {
		return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(description = "Get client by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "clientId", required = true, example = "23234ads-2424323fsae32efwqsd23-12esa")
	public ResponseEntity<Client> getClient(@PathVariable("id") String clientId) {
		return clientService.getClient(clientId)
							.map(ResponseEntity::ok)
							.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Operation(description = "Save a client")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Client> save(@RequestBody Client client) {
		return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Delete client by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "clientId", required = true, example = "23234ads-2424323fsae32efwqsd23-12esa")
	public ResponseEntity<Void> delete(@PathVariable("id") String clientId) {
		if (clientService.delete(clientId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
