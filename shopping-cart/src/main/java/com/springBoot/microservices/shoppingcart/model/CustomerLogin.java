package com.springBoot.microservices.shoppingcart.model;



public class CustomerLogin {
	private int id;
	private String email;
	private String password;
	private String role;
	private CustomerProfile customer;
	
	 public CustomerProfile getCustomer() {
			return customer;
		}
		public void setCustomer(CustomerProfile customer) {
			this.customer = customer;
		}
	@Override
		public String toString() {
			return "Password [id=" + id + ", email=" + email + ", password=" + password+
					", role=" + role + "]";
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public CustomerLogin(int id,String email,String role,String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role=role;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public CustomerLogin() {
		super();
	}
	
	
}
