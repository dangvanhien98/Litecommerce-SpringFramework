package com.litecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.OrderDetailModel;
import com.litecommerce.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	public void insertOrderDetail(OrderDetailModel order) {
		orderDetailRepository.insertOrderDetail(order.getQuantity(), order.getOrderID(), order.getProductID());
	}
}
