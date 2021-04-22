package com.springBoot.microservices.shoppingcart.model;

import java.util.Date;

public class CustomerProfile {
	private int cp_id;  
	private String fname;  
	private String lname;  
	private String  phno;  
	private Address address;
	private Date dob;
	private CustomerLogin customerLogin;
	public CustomerProfile() {
		super();
		}

	public CustomerProfile(int cp_id,String fname,String lname,String phno,  Address address, 
			Date dob,CustomerLogin customerLogin) {
		super();
		this.cp_id = cp_id;
		this.fname = fname;
		this.lname = lname;
		this.phno = phno;
		this.address = address;
		this.dob = dob;
		this.customerLogin = customerLogin;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob= dob;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public int getCp_id() {
		return cp_id;
	}

	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}

	public CustomerLogin getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(CustomerLogin customerLogin) {
		this.customerLogin = customerLogin;
	}

	
}
