package com.litecommerce.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.CustomerModel;
import com.litecommerce.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String registerCustomer(Model model) {
		model.addAttribute("customer", new CustomerModel());
		return "web/registerCustomer";
	}
	
	@RequestMapping(value = "/check-signup", method = RequestMethod.POST)
	public String checkSignup(@ModelAttribute("customer") CustomerModel customer, @RequestParam String password1) {
		if(customer.getPassWord().equals(password1) && customerService.findByPhone(customer.getNumberPhone()) == null ) {
			customerService.save(customer);
			return "redirect:/login";
		}		
		return "web/registerCustomer";
	}
}
