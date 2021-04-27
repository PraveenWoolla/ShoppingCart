package com.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entities.Product;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product)
	{
		Product product1 = this.productRepository.save(product);
		return product1;
	}

	@Override
	public List<Product> getProducts()
	{
        List<Product> products = this.productRepository.loadAll();
        if(products==null) {
			throw new NullPointerException();
		}
        System.out.println(products);
		return products;
	}
	@Override
	public void deleteProduct(int pid)
	{
         this.productRepository.deleteById(pid);
	}
	@Override
	public Product getProduct(int pid)
	{
	
		Product product = this.productRepository.findByPid(pid);
		if(product==null) {
			throw new NullPointerException();
		}
		return product;
	}
	@Override
	public List<Product> getProductsByColor(String color) {
		List<Product> products = this.productRepository.findByColor(color);
		if(products==null) {
			throw new NullPointerException();
		}
		return products;
	}
	@Override
	public List<Product> getProductsByName(String name) {
		List<Product> products = this.productRepository.findByName(name);
		if(products==null) {
			throw new NullPointerException();
		}
		return products;
	}
	@Override
	public List<Product> getProductsByCategory(String category) {
		List<Product> products = this.productRepository.findByCategory(category);
		if(products==null) {
			throw new NullPointerException();
		}
		return products;
	}
	@Override
	public List<Product> getProductsByColorAndCategory(String color, String category) {
		List<Product> products = this.productRepository.findByColorAndCategory(color, category);
		if(products==null) {
			throw new NullPointerException();
		}
		return products;
	}

}
