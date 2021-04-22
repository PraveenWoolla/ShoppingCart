package com.springBoot.microservices.shoppingcart.model;

public class Address {
	
	private int id;
	private String street;
	private String city;
	private String state;

	private CustomerProfile customer;
	public Address() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public CustomerProfile getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerProfile customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + "]";
	}
	public Address(int id, String street, String city, String state, CustomerProfile customer) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.customer = customer;
	}
	
	
}
