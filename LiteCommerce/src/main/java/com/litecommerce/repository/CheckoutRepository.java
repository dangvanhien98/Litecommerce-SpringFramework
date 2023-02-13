package com.litecommerce.repository;

import java.sql.Time;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.CheckoutModel;

public interface CheckoutRepository extends CrudRepository<CheckoutModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into checkouts(address, city, date, payment, time, customerid, orderid) values(?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	void insertCheckout(String address, String city, Date date, String payment, Time time, int customerId, int orderId);
	
}
