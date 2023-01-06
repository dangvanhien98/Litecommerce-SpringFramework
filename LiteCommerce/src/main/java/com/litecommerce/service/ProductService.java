package com.litecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.ProductModel;
import com.litecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository prRepository;
	
	public List<ProductModel> findAll(){
		return prRepository.findAll();
	}
	
	public ProductModel findById(int id){
		return prRepository.findById(id);
	}
	
	public List<ProductModel> getNewArrivalsProduct(){
		return prRepository.getNewArrivalsProduct();
	}
	
	public List<ProductModel> getNewArrivalsProductByGroupId(Integer id){
		return prRepository.getNewArrivalsProductByGroupId(id);
	}
	
	public List<ProductModel> getAllProductByGroupId(Integer id){
		return prRepository.getAllProductByGroupId(id);
	}
	
	public List<ProductModel> getAllProduct(){
		return prRepository.getAllProduct();
	}
	
	public void deleteById(Integer id) {
		prRepository.deleteById(id);
	}
	
	public void updateQuantity(int quantity, int productid) {
		prRepository.updateQuantity(quantity, productid);
	}
}
