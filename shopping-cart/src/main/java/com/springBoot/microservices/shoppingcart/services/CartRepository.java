package com.springBoot.microservices.shoppingcart.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.microservices.shoppingcart.model.Cart;

public interface CartRepository extends CrudRepository<Cart,Integer>{

	List<Cart> findById(int ct_id);

	List<Cart> findByCtid(int ct_id);

	int deleteByCtidAndPdid(int ctid, int pdid);
    
	//@Query(value="delete u from Cart u where u.ctid=:ctid and u.pdid=:pdid",nativeQuery=true)
	//void deleteByCtidAndPdid(@Param("ctid") Integer ctid, @Param("pdid") Integer pdid);

}
