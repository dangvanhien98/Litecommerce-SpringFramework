package com.litecommerce.model;

public class CartModel {
	private ProductModel product;
	private int quantity;
	public CartModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartModel(ProductModel product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getTotal() {
		 return this.quantity* this.getProduct().getPrice();
	}
	
	
	
}
