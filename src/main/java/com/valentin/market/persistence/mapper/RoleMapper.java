package com.valentin.market.persistence.mapper;

import com.valentin.market.domain.Role;
import com.valentin.market.persistence.entity.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	@Mappings({
			@Mapping(source = "id", target = "roleId"),
			@Mapping(source = "name", target = "name")
	})
	Role toRole(Rol rol);
	List<Role> toRoles(List<Rol> rols);

	@InheritInverseConfiguration
	Rol toRol(Role role);
}
