package com.valentin.market.domain.service;

import com.valentin.market.domain.Category;
import com.valentin.market.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAll() {
		return categoryRepository.getAll();
	}

	public Optional<Category> getCategory(Integer categoryId) {
		return categoryRepository.getCategory(categoryId);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Boolean delete(Integer categoryId) {
		return getCategory(categoryId).map(category -> {
										  categoryRepository.delete(categoryId);
										  return true;
									  })
									  .orElse(false);
	}
}
