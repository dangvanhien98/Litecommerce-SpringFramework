package com.litecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {
	private ProductModel product;
	private int quantity;
	
	public float getTotal() {
		 return this.quantity* this.getProduct().getPrice();
	}
}
