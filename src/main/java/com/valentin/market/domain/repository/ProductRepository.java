package com.valentin.market.domain.repository;

import com.valentin.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
	List<Product> getAll();
	Optional<List<Product>> getByCategory(Integer categoryId);
	Optional<Product> getProduct(Integer productId);
	Product save(Product product);
	void delete(Integer productId);
}
