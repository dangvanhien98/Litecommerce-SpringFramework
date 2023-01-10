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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Customers")
@Entity(name = "CustomerModel")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
}
