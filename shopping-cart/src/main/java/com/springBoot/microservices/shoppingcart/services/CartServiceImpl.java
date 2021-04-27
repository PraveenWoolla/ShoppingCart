package com.springBoot.microservices.shoppingcart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.microservices.shoppingcart.model.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cart;
	
	@Override
	public Cart addToCart(int ct_id, int pd_id)
	{
		int id=0;
		Cart c=cart.save(new Cart(id,ct_id,pd_id));
		return c;
	}
	
	@Override
	public List<Cart> getAllCarts(int ct_id) {
		List<Cart> carts=cart.findByCtid(ct_id);
		if(carts==null) {
			throw new NullPointerException();
		}
		return carts;
	}
	
	@Transactional
	@Override
	public int deleteFromCart(int ctid, int pdid) {
		int c=cart.deleteByCtidAndPdid(ctid, pdid);
		return c;
	}
	
	

}
