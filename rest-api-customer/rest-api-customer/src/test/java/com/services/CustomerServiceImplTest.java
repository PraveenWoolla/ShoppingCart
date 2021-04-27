package com.customer.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
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
import static org.mockito.Matchers.*;

import com.customer.entities.Address;
import com.customer.entities.CustomerLogin;
import com.customer.entities.CustomerProfile;
@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {
	@InjectMocks
	CustomerServiceImpl service;
	@Mock
	CustomerProfileRepository repo1;
	@Mock
	CustomerLoginRepository repo2;


	Address address = new Address(1,"Goutham Street", "Palasa", "Ap");
	CustomerLogin customerLogin = new CustomerLogin(1, "praveen.woolla@gmail.com", "User", "Praveen341");
	CustomerProfile customer = new CustomerProfile(3, "Praveen", "Woolla", "7659801358", address,
			Date.valueOf(LocalDate.of(1999, 8, 20)), customerLogin);
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testCreateCustomer() {
		when(repo1.save(Mockito.any(CustomerProfile.class))).thenReturn(customer);
		CustomerProfile c1=service.createCustomer(customer);
		assertNotNull(c1);
		verify(repo1, times(1)).save(customer);
	}

	@Test
	void testGetCustomers() {
		when(repo1.getAllCustomers()).thenReturn(Arrays.asList(customer));
		List<CustomerProfile> c1=service.getCustomers();
		assertEquals(1, c1.size());
		verify(repo1, times(1)).getAllCustomers();
	}
    @Test
	void testGetCustomersIfItThrowsNullPointerException() {
		when(repo1.getAllCustomers()).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getCustomers());
	}
	@Test
	void testDeleteCustomer() {
		 doNothing().when(repo1).deleteById(anyInt());
		  service.deleteCustomer(anyInt());
		  verify(repo1,times(1)).deleteById(anyInt());
	}

	@Test
	void testGetCustomerById() {
		when(repo1.findByCpid(anyInt())).thenReturn(customer);
		CustomerProfile c1=service.getCustomerById(1);
		assertEquals("Praveen", c1.getFname());
		assertEquals("Woolla", c1.getLname());
		assertEquals("7659801358", c1.getPhno());
		verify(repo1, times(1)).findByCpid(anyInt());
	}
	

	@Test
	void testGetCustomerByIdIfItThrowsNullPointerException() {
		when(repo1.findByCpid(anyInt())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getCustomerById(anyInt()));
		
	}


	@Test
	void testGetCustomerByemail() {
		when(repo2.findByEmail(anyString())).thenReturn(customerLogin);
		CustomerLogin c1=service.getCustomerByemail(anyString());
		assertEquals("praveen.woolla@gmail.com",c1.getEmail());
	}
	
	@Test
	void testGetCustomerByemailIfItThrowsNullPointerException() {
		when(repo2.findByEmail(anyString())).thenReturn(null);
		assertThrows(NullPointerException.class,()->service.getCustomerByemail(anyString()));
	}

}
