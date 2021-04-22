package com.springBoot.microservices.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springBoot.microservices.shoppingcart.model.Cart;
import com.springBoot.microservices.shoppingcart.services.CartServiceImpl;

@SpringBootTest
class ShoppingCartApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private CartServiceImpl cart;

	@Test
	public void service()
	{
		List<Cart> carts=cart.getAllCarts(82);
		List<Cart> carts2=cart.getAllCarts(82);
		for(int i=0;i<carts.size();i++)
		{
			Cart cart1=carts.get(i);
			System.out.println(cart1);
			Cart cart2=carts2.get(i);
			System.out.println(cart2);
			assertEquals(cart1.toString(),cart2.toString());
		}
		//assertEquals(carts2.get(0),carts.get(0));
	}
	@Test
	public void postService()
	{
		Cart cart2=cart.addToCart(82,18);
		System.out.println(cart2);
		assertEquals(82,cart2.getCtid());
	}
}
