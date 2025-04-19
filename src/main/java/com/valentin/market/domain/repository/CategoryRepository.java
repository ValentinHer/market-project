package com.valentin.market.domain.repository;

import com.valentin.market.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
	List<Category> getAll();
	Optional<Category> getCategory(Integer categoryId);
	Category save(Category category);
	void delete(Integer categoryId);
}
