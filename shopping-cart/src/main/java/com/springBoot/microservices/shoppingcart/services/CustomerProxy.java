package com.springBoot.microservices.shoppingcart.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springBoot.microservices.shoppingcart.model.CustomerLogin;
import com.springBoot.microservices.shoppingcart.model.CustomerProfile;

@FeignClient(name="customer-service")
public interface CustomerProxy {
		@GetMapping("/customer/id/{id}")
		public CustomerProfile customer(@PathVariable("id") int id);
		
		@DeleteMapping("/customer/{id}")
		public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id);

		@GetMapping("/customer/{email}")
		public CustomerLogin customerByEmail(@PathVariable("email") String email);


		
	
}
