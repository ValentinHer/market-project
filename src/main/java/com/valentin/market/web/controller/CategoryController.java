package com.valentin.market.web.controller;

import com.valentin.market.domain.Category;
import com.valentin.market.domain.service.CategoryService;
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
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") Integer categoryId) {
		return categoryService.getCategory(categoryId)
							  .map(ResponseEntity::ok)
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Integer categoryId) {
		if(categoryService.delete(categoryId)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
