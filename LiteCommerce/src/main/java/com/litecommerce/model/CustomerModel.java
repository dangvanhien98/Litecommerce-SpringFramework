package com.litecommerce.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Customers")
@Entity(name = "CustomerModel")
public class CustomerModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CustomerID", columnDefinition = "int")
	private int customerID;
	
	@Column(name = "Email", columnDefinition = "nvarchar(50)")
	private String email;
	
	@Column(name = "NumberPhone", columnDefinition = "nvarchar(50)")
	private String numberPhone;
	
	@Column(name = "PassWord", columnDefinition = "nvarchar(50)")
	private String passWord;

	@Column(name = "CustomerName", columnDefinition = "nvarchar(50)")
	private String customerName;

	@Column(name = "Address", columnDefinition = "nvarchar(50)")
	private String address;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Collection<OrderModel> orders;

	public CustomerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerModel(int customerID, String email, String numberPhone, String passWord, String customerName,
			String address, Collection<OrderModel> orders) {
		super();
		this.customerID = customerID;
		this.email = email;
		this.numberPhone = numberPhone;
		this.passWord = passWord;
		this.customerName = customerName;
		this.address = address;
		this.orders = orders;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Collection<OrderModel> getOrders() {
		return orders;
	}

	public void setOrders(Collection<OrderModel> orders) {
		this.orders = orders;
	}
	
}
