package com.litecommerce.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.litecommerce.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = {"/admin-employee", "/admin-employee/{action}/{id}"}, method = RequestMethod.GET)
	public String emPloyeePage(Model model, @PathVariable(required = false) String action, @PathVariable(required = false) Integer id) {
		System.out.println(action);
		if(action != null && action.equals("delete")) {
			System.out.println(id);
			employeeService.deleteById(id);
		}
		model.addAttribute("listEmployee", employeeService.getAllEmployee());
		return "admin/employee";
	}
}
