package com.litecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "AccountModel")
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AccountID", columnDefinition = "int")
	private int accountId;
	
	@Column(name = "UserName", columnDefinition = "nvarchar(50)")
	private String username;
	
	@Column(name = "PassWord", columnDefinition = "nvarchar(50)")
	private String password;
	
	@Column(name = "Role", columnDefinition = "int")
	private int role;

}
