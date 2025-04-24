package com.valentin.market.persistence.crud;

import com.valentin.market.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, String> {
	Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}
