package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Product;
import com.product.services.ProductServiceImpl;

@RestController
public class ProductController {

	@Autowired
	private ProductServiceImpl productdao;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts()
	{
		List<Product> products = productdao.getProducts();
		return ResponseEntity.of(Optional.of(products));
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(value = "id") int id)
	{
		Product product = productdao.getProduct(id);
		if(product==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(product));
	}

	@GetMapping("/products/color/{color}")
	public ResponseEntity<List<Product>> getProductsByColor(@PathVariable(value = "color") String color) {
		List<Product> products = productdao.getProductsByColor(color);
		return ResponseEntity.of(Optional.of(products));
	}

	@GetMapping("/products/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable(value = "name") String name) {
		List<Product> products = productdao.getProductsByName(name);
		return ResponseEntity.of(Optional.of(products));
	}

	@GetMapping("/products/{categoty}")
	public ResponseEntity<List<Product>> getProductsByCategoty(@PathVariable(value = "categoty") String categoty) {
		List<Product> products = productdao.getProductsByCategory(categoty);
		if (products == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(products));
	}

	@GetMapping("/products/{color}/{categoty}")
	public ResponseEntity<List<Product>> getProductsByColorAndCategoty(@PathVariable(value = "color") String color,@PathVariable(value = "categoty") String category) 
	{
		List<Product> products = productdao.getProductsByColorAndCategory(color, category);
		if (products == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(products));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		this.productdao.createProduct(product);
		return ResponseEntity.of(Optional.of(product));
		// return ResponseEntity.ok("Succesfully Created!!");
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") int id)
	{
		this.productdao.deleteProduct(id);
		return ResponseEntity.ok("Successfull Deleted!!");
	}

	
}