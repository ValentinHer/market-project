package com.valentin.market.web.controller;

import com.valentin.market.domain.Product;
import com.valentin.market.domain.service.ProductService;
import com.valentin.market.web.config.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@Operation(description = "Get all supermarket products")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Product>> getAll() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(description = "Search a product by id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Product Not Found", content = @Content)
	})
	@Parameter(name = "id", required = true, example = "30")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer productId) {
		return productService.getProduct(productId)
							 .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
							 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") Integer categoryId) {
		return productService.getByCategory(categoryId)
							 .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
							 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Integer productId) {
		if (productService.delete(productId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
