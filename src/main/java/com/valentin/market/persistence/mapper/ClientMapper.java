package com.valentin.market.persistence.mapper;

import com.valentin.market.domain.Client;
import com.valentin.market.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface ClientMapper {

	@Mappings({
			@Mapping(source = "id", target = "clientId"),
			@Mapping(source = "idRol", target = "roleId"),
			@Mapping(source = "nombre", target = "name"),
			@Mapping(source = "apellidos", target = "lastname"),
			@Mapping(source = "celular", target = "numberPhone"),
			@Mapping(source = "direccion", target = "address"),
			@Mapping(source = "correoElectronico", target = "email"),
			@Mapping(source = "contrasenia", target = "password"),
			@Mapping(source = "disabled", target = "disabled"),
			@Mapping(source = "rol", target = "role")
	})
	Client toClient(Cliente cliente);
	List<Client> toClients(List<Cliente> clientes);

	@InheritInverseConfiguration
	@Mapping(target = "compras", ignore = true)
	Cliente toCliente(Client client);
}
