package com.springBoot.microservices.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int ctid;
	private int pdid;
	
	public Cart() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPdid() {
		return pdid;
	}
	public void setPdid(int pd_id) {
		this.pdid = pd_id;
	}
	public Cart(int id,int ct_id, int pd_id) {
		super();
		this.id = id;
		this.pdid = pd_id;
		this.ctid=ct_id;
	}
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ct_id) {
		this.ctid = ct_id;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", ctid=" + ctid + ", pdid=" + pdid + "]";
	}
	
	
}
