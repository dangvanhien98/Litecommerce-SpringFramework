package com.litecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.litecommerce.model.CartModel;

public class CartService {
	public List<CartModel> carts = new ArrayList<CartModel>();

	public void addToCart(CartModel cart) {
		for (int i = 0; i < carts.size(); i++) {
			CartModel c = carts.get(i);
			if (c.getProduct().getProductID() == cart.getProduct().getProductID()) {
				c.setQuantity(c.getQuantity() + 1);
				return;
			}
		}
		carts.add(cart);
		
	}

	public void deleteCart(int id) {
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getProduct().getProductID() == id)
				carts.remove(i);
		}
	}

	public void updateCart(int id, int quantity) {
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getProduct().getProductID() == id) {
				carts.get(i).setQuantity(quantity);
				return;
			}
		}
	}

	public float subTotal() {
		float subTotal = 0;
		for (CartModel cart : carts) {
			subTotal += cart.getTotal();
		}
		return subTotal;
	}

	public int quantityById(int id) {
		for (CartModel cart : carts) {
			if (cart.getProduct().getProductID() == id) {
				return cart.getQuantity();
			}
		}
		return 0;
	}
}
