package com.litecommerce.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Table(name = "Checkouts")
@Entity(name = "CheckoutModel")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CheckoutID", columnDefinition = "int")
	private int checkoutID;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private CustomerModel customer;
	
	@ManyToOne
	@JoinColumn(name = "OrderID")
	private OrderModel order;
	
	private String address;
	private String city;
	private Time time;
	private Date date;
	private String payment;
	
	@Transient
	private int customerID;
	
	@Transient
	private int orderID;
}
