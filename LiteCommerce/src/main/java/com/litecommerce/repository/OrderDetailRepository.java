package com.litecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.OrderDetailModel;

public interface OrderDetailRepository extends CrudRepository<OrderDetailModel, Integer> {
	@Transactional
	@Modifying
	@Query(value = "insert into order_detail(quantity, orderid, productid) values (?1, ?2, ?3)", nativeQuery = true)
	void insertOrderDetail(int quantity, int orderID, int productID);
}
