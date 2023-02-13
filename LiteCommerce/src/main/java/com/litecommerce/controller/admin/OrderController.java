package com.litecommerce.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.litecommerce.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value = "admin-order")
	public String showOrder(Model model) {
		model.addAttribute("lstOrder", orderService.findAllOrder());
		return "admin/order";
	}
}
