package com.valentin.market.persistence.crud;

import com.valentin.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

	List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);
}
