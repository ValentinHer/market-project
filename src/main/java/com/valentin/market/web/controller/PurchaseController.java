package com.valentin.market.web.controller;

import com.valentin.market.domain.Purchase;
import com.valentin.market.domain.service.PurchaseService;
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
	public ResponseEntity<List<Purchase>> getAll() {
		return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId) {
		return purchaseService.getByClient(clientId)
							  .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Purchase> getById(@PathVariable("id") Integer purchaseId) {
		return purchaseService.getById(purchaseId)
							  .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Purchase> save(Purchase purchase) {
		return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
	}

	public ResponseEntity delete(Integer purchaseId) {
		if(purchaseService.delete(purchaseId)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
