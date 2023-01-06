package com.litecommerce.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.CartModel;
import com.litecommerce.model.CustomerModel;
import com.litecommerce.model.ProductModel;
import com.litecommerce.service.CartService;
import com.litecommerce.service.CustomerService;
import com.litecommerce.service.GroupProductService;
import com.litecommerce.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	GroupProductService grProductService;
	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;

	CartService cartService = new CartService();

	@RequestMapping(value = "/loginn", method = RequestMethod.GET)
	public String login() {
//		WebSecurityConfig web = new WebSecurityConfig();
//		System.out.println(web.userDetailsService(new BCryptPasswordEncoder()).loadUserByUsername("admin@gmail.com"));
//		try {
//			System.out.println(web.filterChain(new HttpSecurity(null, null, null)));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "web/login";
	}

//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public String admin() {
//		return "web/admin";
//	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String loginSuccess(@RequestParam(required = false) String numberPhone,
			@RequestParam(required = false) String password, HttpSession httpSession, Model model) {
		if (customerService.findByPhoneAndPass(numberPhone, password) != null) {
			if (httpSession.getAttribute("customterLogin") == null) {
				httpSession.setAttribute("customterLogin", customerService.findByPhoneAndPass(numberPhone, password));
			}
			// CustomerModel cus =
			// (CustomerModel)httpSession.getAttribute("customterLogin");
			return "redirect:/";
		}
		return "redirect:/loginn";
	}

	@RequestMapping(value = "/logoutt")
	public String Logout(HttpSession httpSession) {

		CartService cart = (CartService) httpSession.getAttribute("cart");
		if (cart != null) {
			for (int i = 0; i < cart.carts.size(); i++) {
				CartModel c = cart.carts.get(i);
				int quantity = c.getProduct().getQuantity(); // lấy sl trong kho của sp theo SS carts
				int id = c.getProduct().getProductID(); // lấy id sp theo ss carts
				productService.updateQuantity(quantity, id);
			}
		}

		httpSession.removeAttribute("customterLogin");
		httpSession.removeAttribute("cart");
		return "redirect:/loginn";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> findAll() {
		return new ResponseEntity<List<ProductModel>>(productService.findAll(), HttpStatus.OK);
		// return "web/index";
	}

//	
//	@RequestMapping(value = "/findById", method = RequestMethod.GET)
//	public ResponseEntity<Optional<GroupProductModel>> findById(@RequestParam int id) {
//		return new ResponseEntity<Optional<GroupProductModel>>(grProductService.findById(id), HttpStatus.OK);
//		//return "web/index";
//	}
	@RequestMapping(value = { "/", "/arrivals/{id}" }, method = RequestMethod.GET)
	public String homePage(Model model, @PathVariable(required = false) Integer id, HttpSession httpSession) {
		if (httpSession.getAttribute("customterLogin") == null)
			return "redirect:/logoutt";
		model.addAttribute("grproduct", grProductService.findAll().subList(1, grProductService.findAll().size()));
		model.addAttribute("grproduct0", grProductService.findAll());
		if (id != null && id != 0)
			model.addAttribute("product", productService.getNewArrivalsProductByGroupId(id));
		else
			model.addAttribute("product", productService.getNewArrivalsProduct());

		CartService lstcart = (CartService) httpSession.getAttribute("cart");

		if (lstcart != null)
			model.addAttribute("lstCart", lstcart.carts);

		CustomerModel cus = (CustomerModel) httpSession.getAttribute("customterLogin");
		model.addAttribute("customer", cus);

		return "web/index";
	}

	@RequestMapping(value = { "/categories", "/categories/{id}" }, method = RequestMethod.GET)
	public String categoriesPage(Model model, @PathVariable(required = false) Integer id, HttpSession httpSession) {
		model.addAttribute("grproduct0", grProductService.findAll());
		if (id == null || id == 0) {
			model.addAttribute("product", productService.getAllProduct());
			model.addAttribute("all", "true");
		} else {
			model.addAttribute("product", productService.getAllProductByGroupId(id));
		}

		CartService lstcart = (CartService) httpSession.getAttribute("cart");
		if (lstcart != null)
			model.addAttribute("lstCart", lstcart.carts);

		CustomerModel cus = (CustomerModel) httpSession.getAttribute("customterLogin");
		model.addAttribute("customer", cus);

		return "web/categories";
	}

}
