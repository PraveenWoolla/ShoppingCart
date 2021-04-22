package com.customer.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private String street;
	@NotBlank(message="City is Mandatory")
	private String city;
	@NotBlank(message="State is Mandatory")
	private String state;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="address")
	@JsonBackReference
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
	public Address(int id, String street, String city, String state) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
	
}
