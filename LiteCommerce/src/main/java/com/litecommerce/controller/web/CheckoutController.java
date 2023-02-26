package com.litecommerce.controller.web;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.litecommerce.model.CustomerModel;
import com.litecommerce.model.OrderDetailModel;
import com.litecommerce.model.OrderModel;
import com.litecommerce.service.CartService;
import com.litecommerce.service.CheckoutService;
import com.litecommerce.service.OrderDetailService;
import com.litecommerce.service.OrderService;

@Controller
public class CheckoutController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	CheckoutService checkoutService;
	
	@RequestMapping(value = "/checkout")
	public String checkout(HttpSession httpSession, Model model) {
		CartService lstcart = (CartService) httpSession.getAttribute("cart");
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
	
	@RequestMapping(value = "/checkout/buy", method = RequestMethod.POST)
	public String buy(HttpSession httpSession, @RequestParam(name = "payment") String payment, @ModelAttribute(name = "customer") CustomerModel customer) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateOrder = new java.util.Date();
		Time timeOrder = new Time(dateOrder.getTime());
		CartService lstcart = (CartService) httpSession.getAttribute("cart");
		int sizeCart = lstcart.carts.size();
		if(customer != null) {
			OrderModel order = new OrderModel(dateOrder, timeOrder, lstcart.subTotal(), "queue",customer.getCustomerID());
			orderService.insertOrder(order); // them vao order
			int orderIDLast = orderService.getOrderIDLast(customer.getCustomerID(), dateFormat.format(dateOrder),timeOrder).getOrderID();
			if(sizeCart > 0) {
				for(int i=0; i< sizeCart; i++) {
					var orderDetail = new OrderDetailModel()
							.setProductID(lstcart.carts.get(i).getProduct().getProductID())
							.setQuantity(lstcart.carts.get(i).getQuantity())
							.setOrderID(orderIDLast);
					orderDetailService.insertOrderDetail(orderDetail); // them vao orderdetail
					checkoutService.insertCheckout(customer.getAddress(), customer.getCity(), dateOrder, payment, timeOrder, customer.getCustomerID(), orderIDLast);
					httpSession.removeAttribute("cart");
				}
			}
		}
		return "redirect:/";
	}

}
