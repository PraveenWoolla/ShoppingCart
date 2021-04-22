package com.customer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
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

import com.customer.entities.Address;
import com.customer.entities.CustomerLogin;
import com.customer.entities.CustomerProfile;
import com.customer.services.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;


@RunWith(MockitoJUnitRunner.class)
class CustomerControllerTest {

	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerServiceImpl customerService;

	private MockMvc mockMvc;
	ObjectMapper om=new ObjectMapper();
	

	Address address = new Address(1,"Goutham Street", "Palasa", "Ap");
	CustomerLogin customerLogin = new CustomerLogin(1, "praveen.woolla@gmail.com", "User", "Praveen341");
	CustomerProfile customer = new CustomerProfile(3, "Praveen", "Woolla", "7659801358", address,
			Date.valueOf(LocalDate.of(1999, 8, 20)), customerLogin);

	String jsonForm = "{\"cp_id\":3,\"fname\":\"Praveen\",\"lname\":\"Woolla\",\"phno\":\"7659801358\",\"address\":{\"id\":1,\"street\":\"Goutham Street\",\"city\":\"Palasa\",\"state\":\"Ap\"},\"dob\":935087400000,\"customerLogin\":{\"id\":1,\"email\":\"praveen.woolla@gmail.com\",\"password\":\"Praveen341\",\"role\":\"User\"}}";
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	void getCustomerByIdTest() throws Exception {
		when(customerService.getCustomerById(3)).thenReturn(customer);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/customer/id/3").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.fname").value("Praveen")).andReturn();

		String jsonRequest=om.writeValueAsString(customer);
		 assertEquals(jsonForm,result.getResponse().getContentAsString());
		 assertEquals(jsonRequest,result.getResponse().getContentAsString());
	}
	@Test
	void getCustomerByIdTestIfIDNOTPresent() throws Exception {
		when(customerService.getCustomerById(3)).thenReturn(customer);
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/id/4").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is(404))
				.andReturn();

		
	}
	@Test
    void getCustomerByUsernameTest() throws Exception
    {
		String s="praveen.woolla@gmail.com";
        when(customerService.getCustomerByemail(anyString())).thenReturn(customerLogin);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/praveen.woolla@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("praveen.woolla@gmail.com"));
    }
	
	@Test
	void getCustomerByUserNameTestIfIDNOTPresent() throws Exception {
		when(customerService.getCustomerByemail(anyString())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/naaveen.woolla@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is(404))
				.andReturn();

		
	}
	
	@Test
	void createCustomer() throws Exception {
		String jsonRequest=om.writeValueAsString(customer);
		when(customerService.createCustomer(any(CustomerProfile.class))).thenReturn(customer);
		mockMvc.perform(MockMvcRequestBuilders.
				post("/create").
				content(jsonRequest).contentType(MediaType.APPLICATION_JSON).
				accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deleteCustomer()throws Exception {
		
		doNothing().when(customerService).deleteCustomer(anyInt());
		mockMvc.perform(MockMvcRequestBuilders.delete("/customer/3").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
    void getCustomersAllCustomersTest() throws Exception
    {
        when(customerService.getCustomers()).thenReturn(Arrays.asList(customer,new CustomerProfile()));
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
       
    }
	
	
	@Test 
	void updateCustomerDetails() throws Exception {
		//String jsonRequest=om.writeValueAsString(customer);
		String jsonForm = "{\"cp_id\":3,\"fname\":\"Naveen\",\"lname\":\"Woolla\",\"phno\":\"7659801358\",\"address\":{\"id\":1,\"street\":\"Goutham Street\",\"city\":\"Palasa\",\"state\":\"Ap\"},\"dob\":935087400000,\"customerLogin\":{\"id\":1,\"email\":\"praveen.woolla@gmail.com\",\"password\":\"Praveen341\",\"role\":\"User\"}}";
		when(customerService.createCustomer(any(CustomerProfile.class))).thenReturn(customer);
		mockMvc.perform(MockMvcRequestBuilders.
				put("/customer/3").
				content(jsonForm).contentType(MediaType.APPLICATION_JSON).
				accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
