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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Employees")
@Entity(name = "EmployeeModel")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
