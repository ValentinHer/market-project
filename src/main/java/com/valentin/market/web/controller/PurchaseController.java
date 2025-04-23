package com.valentin.market.web.controller;

import com.valentin.market.domain.Purchase;
import com.valentin.market.domain.service.PurchaseService;
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
@RequestMapping("/purchases")
public class PurchaseController {
	@Autowired
	PurchaseService purchaseService;

	@GetMapping
	@Operation(description = "Get all purchases")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Purchase>> getAll() {
		return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/cliente/{id}")
	@Operation(description = "Get purchases by client id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "clientId", required = true, example = "12eec-121312adsad-121eas")
	public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId) {
		return purchaseService.getByClient(clientId)
							  .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/{id}")
	@Operation(description = "Get a purchase by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "purchaseId", required = true, example = "12")
	public ResponseEntity<Purchase> getById(@PathVariable("id") Integer purchaseId) {
		return purchaseService.getById(purchaseId)
							  .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Operation(description = "Save a purchase")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
		return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Delete a purchase by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "purchaseId", required = true, example = "2")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer purchaseId) {
		if(purchaseService.delete(purchaseId)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
