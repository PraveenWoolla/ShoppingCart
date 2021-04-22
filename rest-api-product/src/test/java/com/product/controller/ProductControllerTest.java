package com.product.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.entities.Product;
import com.product.services.ProductServiceImpl;
@RunWith(MockitoJUnitRunner.class)
class ProductControllerTest {
	
	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductServiceImpl productService;

	private MockMvc mockMvc;
	
	ObjectMapper om=new ObjectMapper();
	Product product=new Product(1,"VivoV11","Touch","Mobile", "Blue",22000);
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	

	@Test
	void getAllProductsTest() throws Exception {
		 when(productService.getProducts()).thenReturn(Arrays.asList(product,new Product()));
	        mockMvc.perform(MockMvcRequestBuilders.get("/products")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andDo(print())
	                .andExpect(status().isOk());
		
	}
	
	@Test
	void getProductByIdTest() throws Exception {
		when(productService.getProduct(anyInt())).thenReturn(product);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/product/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("VivoV11")).andReturn();

		String jsonRequest=om.writeValueAsString(product);
		 assertEquals(jsonRequest,result.getResponse().getContentAsString());
	}
	
	@Test
	void getProductByIdIfNotPresentTest() throws Exception {
		when(productService.getProduct(1)).thenReturn(product);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/product/2").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is(404)).andReturn();
				
	}

	@Test
	void getProductByColorTest() throws Exception {
		when(productService.getProductsByColor(anyString())).thenReturn(Arrays.asList(product));
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/color/Blue").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	void getProductByNameTest() throws Exception {
		when(productService.getProductsByName(anyString())).thenReturn(Arrays.asList(product));
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/name/VivoV11").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
		
	}
	@Test
	void getProductByCatagoryTest() throws Exception {
		when(productService.getProductsByCategory(anyString())).thenReturn(Arrays.asList(product));
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/Mobile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	void getProductByCatagoryIfItNotPresentTest() throws Exception {
		when(productService.getProductsByCategory(anyString())).thenReturn(null);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/Dress").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is(404))
				.andReturn();
	}
	@Test
	void getProductsByColorAndCategotyTest() throws Exception {
		when(productService.getProductsByColorAndCategory(anyString(),anyString())).thenReturn(Arrays.asList(product));
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/Blue/Mobile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}
	

	@Test
	void getProductsByColorAndCategotyIfNotPresentTest() throws Exception {
		when(productService.getProductsByColorAndCategory(anyString(),anyString())).thenReturn(null);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/products/Black/Mobile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is(404))
				.andReturn();
	}
	

	@Test
	void createProductTest() throws Exception {
		String jsonRequest=om.writeValueAsString(product);
		when(productService.createProduct(product)).thenReturn(product);
		mockMvc.perform(MockMvcRequestBuilders.
				post("/add").
				content(jsonRequest).contentType(MediaType.APPLICATION_JSON).
				accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deleteProductTest() throws Exception {
		doNothing().when(productService).deleteProduct(anyInt());
		mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}

}
