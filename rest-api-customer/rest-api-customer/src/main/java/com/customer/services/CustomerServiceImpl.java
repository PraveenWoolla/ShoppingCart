package com.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entities.CustomerLogin;
import com.customer.entities.CustomerProfile;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerLoginRepository customerLoginRepository;
	@Autowired
	private CustomerProfileRepository CustomerRepository;
	
	@Override
	public CustomerProfile createCustomer(CustomerProfile customer) {
		CustomerProfile add = CustomerRepository.save(customer);
		return add;
	}
	
	@Override
	public List<CustomerProfile> getCustomers() {
		List<CustomerProfile> customers =CustomerRepository.getAllCustomers();
		if(customers==null) {
			throw new NullPointerException();
		}
		return customers;
	}
	
	@Override
	public void deleteCustomer(Integer id) {
		this.CustomerRepository.deleteById(id);
	}

	@Override
	public CustomerProfile getCustomerById(Integer id) {
		CustomerProfile customer = null;
			customer= this.CustomerRepository.findByCpid(id);
			if(customer==null) {
				throw new NullPointerException();
			}
		return customer;
	}
	
    @Override
	public CustomerLogin getCustomerByemail(String email) {
		CustomerLogin customer=this.customerLoginRepository.findByEmail(email);
		if(customer==null) {
			throw new NullPointerException();
		}
		return customer;
	}

}
