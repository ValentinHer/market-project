package com.valentin.market.domain.repository;

import com.valentin.market.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
	List<Role> getAll();
	Optional<Role> getById(Integer roleId);
	Role save(Role role);
	void delete(Integer roleId);
}
