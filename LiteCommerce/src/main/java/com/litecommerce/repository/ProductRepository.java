package com.litecommerce.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.ProductModel;

public interface ProductRepository extends CrudRepository<ProductModel, Integer> {
	List<ProductModel> findAll();
	
	ProductModel findById(int id);
	
	@Query(value = "select top(5) * from products where quantity > 0 order by entry_date desc", nativeQuery = true)
	List<ProductModel> getNewArrivalsProduct();
	
	@Query(value = "select top(5) * from products prd where prd.quantity > 0 and prd.group_productid = ?1 order by prd.entry_date desc", nativeQuery = true)
	List<ProductModel> getNewArrivalsProductByGroupId(Integer group_productid);
	
	@Query(value = "select * from products prd where prd.group_productid = ?1 order by prd.entry_date desc", nativeQuery = true)
	List<ProductModel> getAllProductByGroupId(Integer id);
	
	@Query(value = "select * from products prd order by prd.entry_date desc", nativeQuery = true)
	List<ProductModel> getAllProduct();
	
	void deleteById(Integer id);
	
	@Transactional
	@Modifying
	@Query(value = "Update products set quantity = ?1 where productid = ?2", nativeQuery = true)
	void updateQuantity(int quantity, int productid);
}
