package com.valentin.market.web.controller;

import com.valentin.market.domain.Category;
import com.valentin.market.domain.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	@Operation(description = "Get all categories")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(description = "Get a category by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "categoryId", required = true, example = "3")
	public ResponseEntity<Category> getById(@PathVariable("id") Integer categoryId) {
		return categoryService.getCategory(categoryId)
							  .map(ResponseEntity::ok)
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Operation(description = "Save a category")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Delete category by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
	})
	@Parameter(name = "categoryId", required = true, example = "2")
	public ResponseEntity delete(@PathVariable("id") Integer categoryId) {
		if(categoryService.delete(categoryId)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
