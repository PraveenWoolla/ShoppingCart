package com.springBoot.microservices.shoppingcart.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springBoot.microservices.shoppingcart.model.CustomerLogin;
import com.springBoot.microservices.shoppingcart.services.CustomerProxy;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private CustomerProxy proxy2 = null;
   
  
   
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomerLogin user=this.proxy2.customerByEmail(email);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not Found");
		}
		
		MyUserDetails userdetails=new MyUserDetails(user);
		return userdetails;
	}

}
