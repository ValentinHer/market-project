package com.valentin.market.domain.service;

import com.valentin.market.domain.Product;
import com.valentin.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.getAll();
	}

	public Optional<Product> getProduct(Integer productId) {
		return productRepository.getProduct(productId);
	}

	public Optional<List<Product>> getByCategory(Integer categoryId) {
		return productRepository.getByCategory(categoryId);
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Boolean delete(Integer productId) {
		return getProduct(productId).map(product -> {
			productRepository.delete(productId);
			return true;
		}).orElse(false);
	}
}
