package com.litecommerce.service;

import java.sql.Time;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.repository.CheckoutRepository;

@Service
public class CheckoutService {
	
	@Autowired
	CheckoutRepository checkoutRepository;
	
	public void insertCheckout(String address, String city, Date date, String payment, Time time, int customerId, int orderId) {
		checkoutRepository.insertCheckout(address, city, date, payment, time, customerId, orderId);
	}
	
}
