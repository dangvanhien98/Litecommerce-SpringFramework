package com.litecommerce.repository;

import java.sql.Time;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into orders(sale_date, sale_time, status, total_price, customerid) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	void insertOrder(java.util.Date date, Time time, String status, float total, int customerid);
	
	@Query(value = "select Top 1 * from orders where customerid = ?1 and sale_date = ?2 and sale_time like ?3%", nativeQuery = true)
	OrderModel getOrderID(int customerId, String date, Time time);
}
