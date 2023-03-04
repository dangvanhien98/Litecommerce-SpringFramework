package com.litecommerce.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.litecommerce.model.EmployeeModel;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer> {
	
	List<EmployeeModel> findAll();
	
	void deleteById(Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "insert into employees (address , birth_date, employee_name, number_phone, accountid) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	void insertEmployee( String address, Date birthDate, String employeeName, String numberPhone, int accountID);
}
