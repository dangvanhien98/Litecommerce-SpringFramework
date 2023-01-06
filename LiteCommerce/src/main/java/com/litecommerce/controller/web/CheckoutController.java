package com.litecommerce.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litecommerce.model.CustomerModel;
import com.litecommerce.service.CartService;

@Controller
public class CheckoutController {
	
	@RequestMapping(value = "/checkout")
	public String checkout(HttpSession httpSession, Model model) {
		CartService lstcart = (CartService) httpSession.getAttribute("cart");
		System.out.println("aaaaaaaa"+lstcart.subTotal());
		if (lstcart != null) {
			model.addAttribute("lstCart", lstcart.carts);
			model.addAttribute("subTotal", lstcart.subTotal());
		}
		CustomerModel cus = (CustomerModel)httpSession.getAttribute("customterLogin");
		model.addAttribute("customer", cus);
		if(httpSession.getAttribute("customterLogin") == null)
			return "web/login";
		return "web/checkout";
	}

}
