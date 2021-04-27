package com.springBoot.microservices.shoppingcart.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.springBoot.microservices.shoppingcart.model.Cart;
import com.springBoot.microservices.shoppingcart.model.Product;
@RunWith(MockitoJUnitRunner.class)
class CartServiceImplTest {

	@InjectMocks
	CartServiceImpl service;
	
	@Mock
	CartRepository repo;
	
	 Cart cart=new Cart(1,3,5);
	 Product product=new Product("VivoV11","Touch","Mobile", "Blue",22000);
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddToCart() {
		when(repo.save(Mockito.any(Cart.class))).thenReturn(cart);
		Cart c1=service.addToCart(3, 5);
		assertNotNull(c1);
		verify(repo, times(1)).save(Mockito.any(Cart.class));
		
	}

	@Test
	void testGetAllCarts() {
		when(repo.findByCtid(anyInt())).thenReturn(Arrays.asList(cart));
		List<Cart> carts=service.getAllCarts(anyInt());
		assertEquals(1,carts.size());
		verify(repo, times(1)).findByCtid(anyInt());
		}
	

	@Test
	void testGetAllCartsIfItThrowsNullPointerException() {
		when(repo.findByCtid(anyInt())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getAllCarts(anyInt()));
	}
	@Test
	void testDeleteFromCart() {
		when(repo.deleteByCtidAndPdid(anyInt(), anyInt())).thenReturn(1);
		int c=service.deleteFromCart(anyInt(), anyInt());
		assertEquals(1, c);
		verify(repo, times(1)).deleteByCtidAndPdid(anyInt(), anyInt());
		
	}

}
