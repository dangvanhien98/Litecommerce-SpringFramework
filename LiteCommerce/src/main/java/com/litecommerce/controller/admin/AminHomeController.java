package com.litecommerce.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AminHomeController {
	
	@RequestMapping(value = "/adminn")
	public String homePage() {
		return "admin/index";
	}
}
