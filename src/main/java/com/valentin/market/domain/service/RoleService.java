package com.valentin.market.domain.service;

import com.valentin.market.domain.Role;
import com.valentin.market.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAll(){
		return roleRepository.getAll();
	}

	public Optional<Role> getById(Integer roleId){
		return roleRepository.getById(roleId);
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public Boolean delete(Integer roleId){
		return getById(roleId).map(role -> {
			roleRepository.delete(roleId);
			return true;
		}).orElse(false);
	}
}
