package com.valentin.market.persistence;

import com.valentin.market.domain.Role;
import com.valentin.market.domain.repository.RoleRepository;
import com.valentin.market.persistence.crud.RolCrudRepository;
import com.valentin.market.persistence.entity.Rol;
import com.valentin.market.persistence.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepository implements RoleRepository {

	@Autowired
	private RolCrudRepository rolCrudRepository;

	@Autowired
	private RoleMapper mapper;

	@Override
	public List<Role> getAll() {
		return mapper.toRoles((List<Rol>) rolCrudRepository.findAll());
	}

	@Override
	public Optional<Role> getById(Integer roleId) {
		return rolCrudRepository.findById(roleId).map(rol -> mapper.toRole(rol));
	}

	@Override
	public Role save(Role role) {
		Rol rol = mapper.toRol(role);

		return mapper.toRole(rolCrudRepository.save(rol));
	}

	@Override
	public void delete(Integer roleId) {
		rolCrudRepository.deleteById(roleId);
	}
}
