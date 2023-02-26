package com.litecommerce.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.AccountModel;
import com.litecommerce.service.AccountService;

@Controller
public class AminHomeController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/admin")
	public String showLogin() {
		return "admin/login";
	}

	@PostMapping(value = "/check-login")
	public String checkLoginAdmin(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password,
			HttpSession session, HttpServletRequest request, Model model) {
		
		AccountModel account = accountService.findByUsernameAndPass(username, password);
		if(account != null) {
			if(session.getAttribute("account") == null) {
				session.setAttribute("account", account);			
			}
			return "redirect:/admin-product";
		}
		return "admin/login";
	}
	
	@GetMapping(value = "/check-logout")
	public String checkLogoutAdmin(HttpSession session) {
		if(session.getAttribute("account") != null) {
			session.removeAttribute("account");
		}
		return "admin/login";
	}
	
}
