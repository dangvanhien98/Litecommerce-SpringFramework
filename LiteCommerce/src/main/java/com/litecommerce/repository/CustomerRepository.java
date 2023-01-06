package com.litecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.CustomerModel;

public interface CustomerRepository extends CrudRepository<CustomerModel, Integer> {
	
	CustomerModel save(CustomerModel customer);
	
	@Query(value = "select * from customers c where c.number_phone = ?1", nativeQuery = true)
	CustomerModel findByPhone(String number_phone);
	
	@Query(value="select * from customers c where c.number_phone = ?1 and c.pass_word = ?2", nativeQuery = true)
	CustomerModel findByPhoneAndPass(String number_phone, String pass_word);
}
