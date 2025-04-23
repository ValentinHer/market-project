package com.valentin.market.web.controller;

import com.valentin.market.domain.Role;
import com.valentin.market.domain.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping
	@Operation(description = "Get all roles")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Role>> getAll() {
		return ResponseEntity.ok(roleService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(description = "Get a role by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "roleId", required = true, example = "2")
	public ResponseEntity<Role> getById(@PathVariable Integer roleId){
		return roleService.getById(roleId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Operation(description = "Create a role")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Role> create(@RequestBody Role role) {
		return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Delete a role by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "roleId", required = true, example = "2")
	public ResponseEntity<Void> delete(@PathVariable Integer roleId){
		if(roleService.delete(roleId)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
