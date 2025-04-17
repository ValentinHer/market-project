package com.valentin.market.persistence;

import com.valentin.market.domain.Product;
import com.valentin.market.domain.repository.ProductRepository;
import com.valentin.market.persistence.crud.ProductoCrudRepository;
import com.valentin.market.persistence.entity.Producto;
import com.valentin.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
	@Autowired
	ProductoCrudRepository productoCrudRepository;

	@Autowired
	private ProductMapper mapper;

	@Override
	public List<Product> getAll() {
		List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}

	@Override
	public Optional<List<Product>> getByCategory(Integer categoryId) {
		List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}

	@Override
	public Optional<Product> getProduct(Integer productId) {
		return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
	}

	@Override
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoCrudRepository.save(producto));
	}

	@Override
	public void delete(Integer productId) {
		productoCrudRepository.deleteById(productId);
	}
}
