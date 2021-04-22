package com.customer.services;

import java.util.List;

import com.customer.entities.CustomerLogin;
import com.customer.entities.CustomerProfile;

public interface CustomerService {
	CustomerProfile createCustomer(CustomerProfile customer);
	List<CustomerProfile> getCustomers();
	void deleteCustomer(Integer id);
	CustomerProfile getCustomerById(Integer id);
	CustomerProfile LoginCustomer(String email, String password);
	CustomerLogin getCustomerByemail(String email);
}
