package com.customer.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Customer_Profile")
public class CustomerProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cp_id;  
	@NotBlank(message="First name is Mandatory!")
	@Size(min=3 ,max=10,message="Length of First Name must inbetween 3 and 10")
	private String fname;  
	@NotBlank(message="Last name is Mandatory!")
	@Size(min=3 ,max=10,message="Length of Last Name must inbetween 3 and 10")
	private String lname;  
	@NotBlank(message="Phone Number is Mandatory!")
	private String  phno;  
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id",referencedColumnName="id")
	@JsonManagedReference
	@Valid
	private Address address;
    @Past
	private Date dob;
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="password_id",referencedColumnName="id")
	@JsonManagedReference
	@Valid
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

	

	@Override
	public String toString() {
		return "CustomerProfile [cp_id=" + cp_id + ", fname=" + fname + ", lname=" + lname + ", phno=" + phno
				+ ", address=" + address + ", dob=" + dob + ", customerLogin=" + customerLogin + "]";
	}
	
}
