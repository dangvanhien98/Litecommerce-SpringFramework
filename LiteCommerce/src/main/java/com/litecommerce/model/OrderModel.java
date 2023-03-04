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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Orders")
@Entity(name = "OrderModel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID", columnDefinition = "int")
	private Integer orderID;

	@Column(name = "SaleDate", columnDefinition = "date")
	private Date saleDate;
	
	@Column(name = "SaleTime", columnDefinition = "nvarchar(25)")
	private String saleTime;
	
	@Column(name = "TotalPrice", columnDefinition = "float")
	private float totalPrice;
	
	@Column(name = "Status", columnDefinition = "nvarchar(25)")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private EmployeeModel employee;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private CustomerModel customer;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<OrderDetailModel> orderDetails;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<CheckoutModel> checkouts;
	
	@Transient
	private int customerID;
	
	public OrderModel(Date saleDate, String saleTime, float totalPrice, String status, int customerID) {
		super();
		this.saleDate = saleDate;
		this.saleTime = saleTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.customerID = customerID;
	}

	

}
