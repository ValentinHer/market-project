package com.valentin.market.persistence;

import com.valentin.market.domain.Category;
import com.valentin.market.domain.repository.CategoryRepository;
import com.valentin.market.persistence.crud.CategoriaCrudRepository;
import com.valentin.market.persistence.entity.Categoria;
import com.valentin.market.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {

	@Autowired
	CategoriaCrudRepository categoriaCrudRepository;

	@Autowired
	CategoryMapper mapper;

	@Override
	public List<Category> getAll() {
		return mapper.toCategories((List<Categoria>) categoriaCrudRepository.findAll());
	}

	@Override
	public Optional<Category> getCategory(Integer categoryId) {
		return categoriaCrudRepository.findById(categoryId).map(categoria -> mapper.toCategory(categoria));
	}

	@Override
	public Category save(Category category) {
		Categoria categoria = mapper.toCategoria(category);

		return mapper.toCategory(categoriaCrudRepository.save(categoria));
	}

	@Override
	public void delete(Integer categoryId) {
		categoriaCrudRepository.deleteById(categoryId);
	}
}
