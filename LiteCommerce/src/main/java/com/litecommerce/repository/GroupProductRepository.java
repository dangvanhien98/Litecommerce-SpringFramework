package com.litecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litecommerce.model.GroupProductModel;

public interface GroupProductRepository extends JpaRepository<GroupProductModel, Integer> {
	
//	List<GroupProductModel> findAll();
//	
//	Optional<GroupProductModel> findById(int id);
}
