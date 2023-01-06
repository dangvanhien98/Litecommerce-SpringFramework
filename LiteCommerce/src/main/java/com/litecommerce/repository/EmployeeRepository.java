package com.litecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.EmployeeModel;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer> {
	
	List<EmployeeModel> findAll();
	
	void deleteById(Integer id);
}
