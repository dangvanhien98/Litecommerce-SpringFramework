package com.litecommerce.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Orders")
@Entity(name = "OrderModel")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OrderID", columnDefinition = "int")
	private int orderID;

	@Column(name = "SaleDate", columnDefinition = "date")
	private Date saleDate;

	@Column(name = "Price", columnDefinition = "float")
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private EmployeeModel employee;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private CustomerModel customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<OrderDetailModel> orderDetails;

}
