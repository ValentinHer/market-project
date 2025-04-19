package com.valentin.market.domain.service;

import com.valentin.market.domain.Product;
import com.valentin.market.domain.Purchase;
import com.valentin.market.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
	@Autowired
	CompraRepository compraRepository;

	public List<Purchase> getAll() {
		return compraRepository.getAll();
	}

	public Optional<List<Purchase>> getByClient(String clientId) {
		return compraRepository.getByClient(clientId);
	}

	public Optional<Purchase> getById(Integer purchaseId) {
		return compraRepository.getById(purchaseId);
	}

	public Purchase save(Purchase purchase) {
		return compraRepository.save(purchase);
	}

	public Boolean delete(Integer purchaseId) {
		return getById(purchaseId).map(compra -> {
									  compraRepository.delete(purchaseId);
									  return true;
								  })
								  .orElse(false);
	}
}
