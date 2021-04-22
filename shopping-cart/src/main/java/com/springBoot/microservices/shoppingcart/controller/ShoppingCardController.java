package com.springBoot.microservices.shoppingcart.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.microservices.shoppingcart.model.Cart;
import com.springBoot.microservices.shoppingcart.model.CustomerProfile;
import com.springBoot.microservices.shoppingcart.model.Product;
import com.springBoot.microservices.shoppingcart.services.CartServiceImpl;
import com.springBoot.microservices.shoppingcart.services.CustomerProxy;
import com.springBoot.microservices.shoppingcart.services.ProductProxy;

@RestController
public class ShoppingCardController {
	@Autowired
	private ProductProxy proxy1 = null;
	
	@Autowired
	private CustomerProxy proxy2 = null;
    
	@Autowired
	private CartServiceImpl cart;
	
	@PostMapping("/add/{ct_id}/{pd_id}")
	public ResponseEntity<String> addToCart(@PathVariable("ct_id") int ct_id,@PathVariable("pd_id") int pd_id)
	{
		Cart c=cart.addToCart(ct_id,pd_id);
		if(c==null)
		{
			throw new NullPointerException();
		}
		return ResponseEntity.ok("successfully added");
	}
	
	@GetMapping("/cart/{ct_id}")
	public List<Product> getFromListOfProductsFromCart(@PathVariable("ct_id") int ct_id)
	{
		List<Cart> carts=cart.getAllCarts(ct_id);
		List<Product> products= carts.stream().map(cart -> {
            Product product = proxy1.getProduct(cart.getPdid());
            return product;
        })
        .collect(Collectors.toList());	
		return products;
	}
	
	@DeleteMapping("/cart/{ct_id}/{pd_id}")
	public ResponseEntity<String> deleteFromCart(@PathVariable("ct_id") int ctid,@PathVariable("pd_id") int pdid)
	{
		int c=cart.deleteFromCart(ctid,pdid);
		if(c==0)
		{
			
			throw new NullPointerException();
		}
		return ResponseEntity.ok("successfully deleted");
		
	}
	
}
