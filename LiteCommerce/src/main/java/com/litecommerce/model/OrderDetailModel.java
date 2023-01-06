package com.litecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "OrderDetail")
@Entity(name = "OrderDetailModel")
public class OrderDetailModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OrderDetailID", columnDefinition = "int")
	private int orderDetailID;
	
	@ManyToOne
	@JoinColumn(name = "ProductID")
	private ProductModel product;
    
	@ManyToOne
	@JoinColumn(name = "OrderID")
	private OrderModel order;

	@Column(name = "Quantity", columnDefinition = "int")
	private int quantity;

	@Column(name = "Price", columnDefinition = "float")
	private float price;

	@Column(name = "Discount", columnDefinition = "float")
	private float discount;

}
