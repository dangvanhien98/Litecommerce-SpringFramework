package com.litecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.CustomerModel;
import com.litecommerce.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public CustomerModel save(CustomerModel customer) {
		return customerRepository.save(customer);
	}
	
	public CustomerModel findByPhone(String number_phone) {
		return customerRepository.findByPhone(number_phone);
	}
	
	public CustomerModel findByPhoneAndPass(String phone, String pass) {
		return customerRepository.findByPhoneAndPass(phone, pass);
	}
}
