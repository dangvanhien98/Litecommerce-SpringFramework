package com.litecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.litecommerce.model.OrderModel;
import com.litecommerce.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public void insertOrder(OrderModel order) {
		orderRepository.insertOrder(order.getSaleDate(), order.getSaleTime(), order.getStatus(), order.getTotalPrice(), order.getCustomerID());
	}
	
	public OrderModel getOrderIDLast(int customerId, String date, String time) {
		return orderRepository.getOrderID(customerId, date, time);
	}
	
	public Page<OrderModel> findAllOrderPage(org.springframework.data.domain.Pageable pageable){
		 return orderRepository.findAllPage(pageable);
	}
	
	public Page<OrderModel> getOrdersByStatus(org.springframework.data.domain.Pageable pageable, String status){
		return orderRepository.getOrdersByStatus(pageable, status);
	}
	
	public void updateOrder(String status, Integer id) {
		orderRepository.updateOrder(status, id);
	}
}
