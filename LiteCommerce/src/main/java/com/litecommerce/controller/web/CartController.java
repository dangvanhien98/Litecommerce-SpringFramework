package com.litecommerce.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.CartModel;
import com.litecommerce.model.CustomerModel;
import com.litecommerce.model.ProductModel;
import com.litecommerce.service.CartService;
import com.litecommerce.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = { "/cart" })
	public String showCart(Model model, HttpSession httpSession) {
		CartService lstcart = (CartService) httpSession.getAttribute("cart");
		if (lstcart != null) {
			model.addAttribute("lstCart", lstcart.carts);
			model.addAttribute("subtotal", lstcart.subTotal());
		}

		CustomerModel cus = (CustomerModel) httpSession.getAttribute("customterLogin");
		model.addAttribute("customer", cus);

		if (lstcart == null)
			return "redirect:/";
		return "web/cart";
	}

	@RequestMapping(value = { "/cart/{action}" })
	public String cart(@PathVariable(required = false) String action, @RequestParam int id,
			@RequestParam(required = false) String quantity, Model model, HttpSession httpSession) {
		CustomerModel cus = (CustomerModel) httpSession.getAttribute("customterLogin");
		model.addAttribute("customer", cus);

		ProductModel product = productService.findById(id);

		CartService cart = null;

		int temp = 0;
		temp = product.getQuantity(); // sl 1 sp trong kho
		System.out.println();
		
		if (action != null && action.equals("add")) {
			if (httpSession.getAttribute("cart") == null) {
				cart = new CartService();
				httpSession.setAttribute("cart", cart);
			}
			cart = (CartService) httpSession.getAttribute("cart");		
			cart.addToCart(new CartModel(product, 1));
			productService.updateQuantity(temp - 1, id); // update sl sp trong kho khi them sp vao gh
		}

		CartService lstcart = (CartService) httpSession.getAttribute("cart");
		
		if (quantity != null)
			temp = product.getQuantity() + lstcart.quantityById(id);
		else
			temp = lstcart.quantityById(id);

		if (action != null && action.equals("delete")) {
			lstcart.deleteCart(id);
			productService.updateQuantity(product.getQuantity()+temp, id); // update sl sp trong kho khi xoa sp trong gh
			if (lstcart.carts.size() == 0) {
				httpSession.removeAttribute("cart");
				return "redirect:/";
			}
		} else if (action != null && action.equals("update")) {
			if(temp-Integer.parseInt(quantity) < 0) {
				System.out.println("out of stock");			
			}
			else {
				productService.updateQuantity(temp - Integer.parseInt(quantity), id); // update sl sp trong kho khi update sl sp
				lstcart.updateCart(id, Integer.valueOf(quantity));
			}
		}

//		if (lstcart == null) {
//			return "redirect:/";
//		}

		model.addAttribute("lstCart", lstcart.carts);
		model.addAttribute("subtotal", lstcart.subTotal());

		return "web/cart";
	}

}
