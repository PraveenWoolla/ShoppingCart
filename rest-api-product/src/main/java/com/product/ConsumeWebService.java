package com.product;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.entities.Product;


//
//@RestController
//public class ConsumeWebService {
//	@Autowired
//   static RestTemplate restTemplate=new RestTemplate();
//   public static void main(String args[])
//   {
//	   callGetAllProduct();
//	   createProducts();
//   }
//   private static void callGetAllProduct()
//   {
//	   HttpHeaders headers = new HttpHeaders();
//	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	      HttpEntity <String> entity = new HttpEntity<String>(headers);
//	     String rest=restTemplate.exchange("http://localhost:8082/products", HttpMethod.GET, entity, String.class).getBody();
//	    	  System.out.println(rest);
//   }
//
////   public static void createProducts() {
////	  Product product=new Product("t-shirt","dfddts","t-shirt","blue",1200);  
////      
////      ResponseEntity<Product> product1 =restTemplate.postForEntity("http://localhost:8082/add",product,Product.class);
////   System.out.println(product1);
// //  }
//}
