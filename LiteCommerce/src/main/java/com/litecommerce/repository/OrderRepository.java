package com.litecommerce.repository;

import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.litecommerce.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into orders(sale_date, sale_time, status, total_price, customerid) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	void insertOrder(java.util.Date date, Time time, String status, float total, int customerid);
	
	@Query(value = "select Top 1 * from orders where customerid = ?1 and sale_date = ?2 and sale_time like ?3%", nativeQuery = true)
	OrderModel getOrderID(int customerId, String date, Time time);
	
	@Query(value = "select * from orders where status like %?1%", nativeQuery = true)
	Page<OrderModel> getOrdersByStatus(Pageable pageable, String status);
	
	@Transactional
	@Modifying
	@Query(value = "update orders set status = ?1 where orderid = ?2", nativeQuery = true)
	void updateOrder(String status, Integer id);
	
	@Query(value = "select * from orders", nativeQuery = true)
	Page<OrderModel> findAllPage(Pageable pageable);
}
