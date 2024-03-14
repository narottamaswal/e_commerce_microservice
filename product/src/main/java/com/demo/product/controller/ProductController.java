package com.demo.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.product.dto.ProductDTO;
import com.demo.product.service.ProductService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long productId) {
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDto) {
		return new ResponseEntity<>(productService.create(productDto), HttpStatus.OK);
	}

	@PostMapping(path = "/update/{id}")
	public ResponseEntity<ProductDTO> create(@PathVariable("id") Long productId, @RequestBody ProductDTO productDto) {
		return new ResponseEntity<>(productService.update(productId, productDto), HttpStatus.OK);
	}
	
	@GetMapping(path = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ProductDTO>> getAllProducts(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "search", defaultValue = "") String search) {
		List<ProductDTO> products = productService.getAllProducts(page, limit, search);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}