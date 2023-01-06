package com.litecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.GroupProductModel;
import com.litecommerce.repository.GroupProductRepository;

@Service
public class GroupProductService {
	@Autowired
	GroupProductRepository grProductRepository;
	
	public List<GroupProductModel> findAll(){
		return grProductRepository.findAll();
	}
	
	public Optional<GroupProductModel> findById(int id){
		return grProductRepository.findById(id);
	}
}
