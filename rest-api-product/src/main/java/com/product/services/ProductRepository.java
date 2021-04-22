package com.product.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.product.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("select u from Product u")
	public List<Product> loadAll();

	List<Product> findByColor(String color);

	List<Product> findByCategory(String category);

	List<Product> findByName(String name);

	List<Product> findByColorAndCategory(String color, String category);
}
