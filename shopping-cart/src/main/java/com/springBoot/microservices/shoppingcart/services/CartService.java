package com.springBoot.microservices.shoppingcart.services;

import java.util.List;

import com.springBoot.microservices.shoppingcart.model.Cart;

public interface CartService {

	Cart addToCart(int ct_id, int pd_id);

	List<Cart> getAllCarts(int ct_id);

	int deleteFromCart(int ctid, int pdid);

	
	
}
