package com.litecommerce.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.litecommerce.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = { "/admin-product", "/admin-product/{action}/{id}"}, method = RequestMethod.GET)
	public String productPage(Model model,@PathVariable(required = false) String action, @PathVariable(required = false) Integer id) {
		System.out.println(action + "--" + id);
		if(action != null && action.equals("delete")) {
			productService.deleteById(id);
		}
		model.addAttribute("listproduct", productService.getAllProduct());
		return "admin/product";
	}
}
