package com.litecommerce.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.AccountModel;
import com.litecommerce.model.EmployeeModel;
import com.litecommerce.service.AccountService;
import com.litecommerce.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@Autowired
	AccountService accountService;

	@RequestMapping(value = { "/admin-employee", "/admin-employee/{action}/{id}" }, method = RequestMethod.GET)
	public String emPloyeePage(Model model, @PathVariable(required = false) String action,
			@PathVariable(required = false) Integer id, HttpSession session) {
		// check login
		if (session.getAttribute("account") != null) {
			AccountModel acc = (AccountModel) session.getAttribute("account");
			model.addAttribute("acc", acc);
		}

		if (action != null && action.equals("delete")) {
			employeeService.deleteById(id);
		}
		model.addAttribute("listEmployee", employeeService.getAllEmployee());
		return "admin/employee";
	}

	@GetMapping(value = { "/admin-addEmployee", "/admin-updateEmployee/{id}" })
	public String formEmployee(Model model, @PathVariable(required = false) Integer id) {
		if(id != null)
			model.addAttribute("isUpdate", "isupdate");
		model.addAttribute("employee", id == null ? new EmployeeModel() : employeeService.getEmployeeById(id));
		return "admin/actionEmployee";
	}

	@PostMapping(value = { "/admin-saveEmployee" })
	public String saveEmployee(@RequestParam Map<String, String> params,
			@ModelAttribute(name = "employee") EmployeeModel employeeModel) {
		String user = params.get("userName");
		String pass = params.get("passWord");
		String pass1 = params.get("passWord1");
		Integer accountid = (Integer.valueOf(params.get("account.accountId")));
		if (accountid == null) {
			if (pass.equals(pass1)) {
				if (accountService.findAccountByUser(user) == null) {
					AccountModel acc = new AccountModel(0, user, pass, 2);
					accountService.saveAccount(acc);
					int idLastAccount = accountService.findAccountByUser(user).getAccountId();
					EmployeeModel em = new EmployeeModel(employeeModel.getAddress(), employeeModel.getBirthDate(),
							employeeModel.getEmployeeName(), employeeModel.getNumberPhone(), idLastAccount);
					employeeService.saveEmployee(em);
				}
			}
		} else {
			String passUpdate = params.get("account.password");
			String employeeName = params.get("employeeName");
			String numberPhone = params.get("numberPhone");
			String address = params.get("address");
			String birthDate = params.get("birthDate");
			accountService.updatePass(passUpdate, accountid);
			EmployeeModel em = new EmployeeModel(address, java.sql.Date.valueOf(birthDate), employeeName, numberPhone,
					accountid);
			employeeService.updateEmployee(em);
		}

		return "redirect:/admin-employee";
	}
}
