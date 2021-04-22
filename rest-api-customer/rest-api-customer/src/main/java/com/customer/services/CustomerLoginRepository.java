package com.customer.services;

import org.springframework.data.repository.CrudRepository;

import com.customer.entities.CustomerLogin;

public interface CustomerLoginRepository extends CrudRepository<CustomerLogin,Integer>{
	
	public CustomerLogin findByEmail(String email);

}
