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
		return customers;
	}
	
	@Override
	public void deleteCustomer(Integer id) {
		this.CustomerRepository.deleteById(id);
	}

	@Override
	public CustomerProfile getCustomerById(Integer id) {
		Optional<CustomerProfile> optional = null;
		CustomerProfile customer = null;
		try
		{
			optional = this.CustomerRepository.findById(id);
			customer = optional.get();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public CustomerProfile LoginCustomer(String email, String password) {
		CustomerProfile cust=null;
		CustomerLogin customerLogin1=customerLoginRepository.findByEmail(email);
		String pass2=customerLogin1.getPassword();
		if(pass2.equals(password))
		{
		    cust=customerLogin1.getCustomer();
		}
	return cust;
	}
	
    @Override
	public CustomerLogin getCustomerByemail(String email) {
		CustomerLogin customer=this.customerLoginRepository.findByEmail(email);
		return customer;
	}

//	@Override
//	public CustomerProfile getCustomerByUsername(String email) {
//		CustomerProfile customer=customerLoginRepository.findByEmail(email);
//		return customer;
//	}
}
