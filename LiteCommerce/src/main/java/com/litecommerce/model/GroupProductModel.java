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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "GroupProduct")
@Entity(name = "GroupProductModel")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GroupProductID", columnDefinition = "int")
	private int groupProductID;

	@Column(name = "GroupProductName", columnDefinition = "nvarchar(50)")
	private String groupProductName;

	@Column(name = "Image", columnDefinition = "nvarchar(50)")
	private String image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "groupProduct", cascade = CascadeType.ALL) // quan hệ 1-n với đối tượng product
	private Collection<ProductModel> products;
}
