package com.springBoot.microservices.shoppingcart.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springBoot.microservices.shoppingcart.model.Product;

@FeignClient(name="product-service")
public interface ProductProxy {
	@GetMapping("/products")
	public List<Product> products();
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable("id") int pr_id);
}
