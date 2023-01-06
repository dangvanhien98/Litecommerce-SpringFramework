package com.litecommerce.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Employees")
@Entity(name = "EmployeeModel")
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EmployeeID", columnDefinition = "int")
	private int employeeID;
	
	@Column(name = "EmployeeName", columnDefinition = "nvarchar(50)")
	private String employeeName;
	
	@Column(name = "NumberPhone", columnDefinition = "nvarchar(50)")
	private String numberPhone;
	
	@Column(name = "Address", columnDefinition = "nvarchar(50)")
	private String address;
	
	@Column(name = "BirthDate", columnDefinition = "date")
	private Date birthDate;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private Collection<OrderModel> orders;

	public EmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeModel(int employeeID, String employeeName, String numberPhone, String address, Date birthDate,
			Collection<OrderModel> orders) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.birthDate = birthDate;
		this.orders = orders;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Collection<OrderModel> getOrders() {
		return orders;
	}

	public void setOrders(Collection<OrderModel> orders) {
		this.orders = orders;
	}
	
	
}
