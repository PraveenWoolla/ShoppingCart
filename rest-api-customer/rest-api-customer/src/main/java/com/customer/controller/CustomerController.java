package com.customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entities.CustomerLogin;
import com.customer.entities.CustomerProfile;
import com.customer.services.CustomerServiceImpl;

@RestController
public class CustomerController {
	@Autowired
	private CustomerServiceImpl dao;
	
	@GetMapping("/customer/id/{id}")
	public ResponseEntity<CustomerProfile> customerById(@PathVariable("id") Integer id) {
		CustomerProfile customer = this.dao.getCustomerById(id);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customer));
	}
	@GetMapping("/customer/{email}")
	public ResponseEntity<CustomerLogin> customerByUsername(@PathVariable("email") String email)
	{
		CustomerLogin customer = this.dao.getCustomerByemail(email);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customer));
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerProfile>> customers() {
		List<CustomerProfile> customers = this.dao.getCustomers();
		if (customers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customers));
	}

	@PostMapping("/create")
	public ResponseEntity<CustomerProfile> createCustomer(@Valid @RequestBody CustomerProfile customer) {
		CustomerProfile b = this.dao.createCustomer(customer);
		return ResponseEntity.of(Optional.of(b));
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
		this.dao.deleteCustomer(id);
		return ResponseEntity.ok("Successfull Deleted!!");
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<CustomerProfile> updateCustomer(@RequestBody CustomerProfile customer,
			@PathVariable("id") Integer id) {
		CustomerProfile b = this.dao.createCustomer(customer);
		return ResponseEntity.of(Optional.of(b));
	}

	@PostMapping(value = "/login")
	public ResponseEntity<CustomerProfile> login(@Valid @RequestBody CustomerLogin customerLogin) {
		CustomerProfile b = this.dao.LoginCustomer(customerLogin.getEmail(), customerLogin.getPassword());
		if (b == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(b));
	}
}
