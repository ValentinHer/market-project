package com.valentin.market.domain.repository;

import com.valentin.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
	List<Purchase> getAll();
	Optional<List<Purchase>> getByClient(String clientId);
	Optional<Purchase> getById(Integer purchaseId);
	Purchase save(Purchase purchase);
	void delete(Integer purchaseId);
}
