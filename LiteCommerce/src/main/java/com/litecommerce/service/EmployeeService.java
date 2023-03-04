package com.litecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litecommerce.model.EmployeeModel;
import com.litecommerce.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<EmployeeModel> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
	}
	
	public void saveEmployee(EmployeeModel em) {
		employeeRepository.insertEmployee( em.getAddress(), em.getBirthDate(), em.getEmployeeName(), em.getNumberPhone(), em.getAccountID());
	}
}
