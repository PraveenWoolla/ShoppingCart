package com.product.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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

import com.product.entities.Product;
@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {
	
	@InjectMocks
	ProductServiceImpl service;
	
	@Mock
	ProductRepository repo;

	Product product=new Product(1,"VivoV11","Touch","Mobile", "Blue",22000);
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testCreateProduct() {
		when(repo.save(Mockito.any(Product.class))).thenReturn(product);
		Product p1=service.createProduct(product);
		assertNotNull(p1);
		verify(repo, times(1)).save(product);	
	}

	@Test
	void testGetProducts() {
		when(repo.loadAll()).thenReturn(Arrays.asList(product));
		List<Product> p1=service.getProducts();
		assertEquals(1,p1.size());
		verify(repo, times(1)).loadAll();
	}

	@Test
	void testGetProductsIfItThrowsNullPointException() {
		when(repo.loadAll()).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProducts()
				);
	}
	@Test
	void testDeleteProduct() {
		 doNothing().when(repo).deleteById(anyInt());
		 service.deleteProduct(anyInt());
		 verify(repo, times(1)).deleteById(anyInt());
		
	}

	@Test
	void testGetProduct() {
		when(repo.findByPid(anyInt())).thenReturn(product);
		Product p1=service.getProduct(anyInt());
		assertEquals("VivoV11",p1.getName());
		assertEquals("Touch",p1.getDescription());
		assertEquals("Mobile",p1.getCategory());
		assertEquals("Blue",p1.getColor());
		assertEquals(22000,p1.getPrice());
		verify(repo, times(1)).findByPid(anyInt());
	}

	@Test()
	void testGetProductIfItThrowsNullPointException() {
		when(repo.findByPid(anyInt())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProduct(anyInt())
		);
	}
	

	@Test
	void testGetProductsByColor() {
		when(repo.findByColor(anyString())).thenReturn(Arrays.asList(product));
		List<Product> p1=service.getProductsByColor(anyString());
		assertEquals(1,p1.size());
		verify(repo, times(1)).findByColor(anyString());
		
		
	}
	@Test
	void testGetProductsByColorIfItThrowsNullPointException() {
		when(repo.findByColor(anyString())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProductsByColor(anyString())
		);
	}

	@Test
	void testGetProductsByName() {
		when(repo.findByName(anyString())).thenReturn(Arrays.asList(product));
		List<Product> p1=service.getProductsByName(anyString());
		assertEquals(1,p1.size());
		verify(repo, times(1)).findByName(anyString());
	}

	@Test
	void testGetProductsByNameIfItThrowsNullPointException() {
		when(repo.findByName(anyString())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProductsByName(anyString())
				);
	}
	@Test
	void testGetProductsByCategory() {
		when(repo.findByCategory(anyString())).thenReturn(Arrays.asList(product));
		List<Product> p1=service.getProductsByCategory(anyString());
		assertEquals(1,p1.size());
		verify(repo, times(1)).findByCategory(anyString());
	}

	@Test
	void testGetProductsByCategoryIfItThrowsNullPointException() {
		when(repo.findByCategory(anyString())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProductsByCategory(anyString())
				);
	}
	
	@Test
	void testGetProductsByColorAndCategory() {
		when(repo.findByColorAndCategory(anyString(),anyString())).thenReturn(Arrays.asList(product));
		List<Product> p1=service.getProductsByColorAndCategory(anyString(),anyString());
		assertEquals(1,p1.size());
		verify(repo, times(1)).findByColorAndCategory(anyString(),anyString());
	}
	
	@Test
	void testGetProductsByColorAndCategoryIfItThrowsNullPointException() {
		when(repo.findByColorAndCategory(anyString(),anyString())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getProductsByColorAndCategory(anyString(),anyString())
				);
	}

}
