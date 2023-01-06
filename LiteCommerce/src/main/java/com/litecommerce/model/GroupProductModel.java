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

@Table(name = "GroupProduct")
@Entity(name = "GroupProductModel")
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

	public GroupProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupProductModel(int groupProductID, String groupProductName, String image,
			Collection<ProductModel> products) {
		super();
		this.groupProductID = groupProductID;
		this.groupProductName = groupProductName;
		this.image = image;
		this.products = products;
	}

	public int getGroupProductID() {
		return groupProductID;
	}

	public void setGroupProductID(int groupProductID) {
		this.groupProductID = groupProductID;
	}

	public String getGroupProductName() {
		return groupProductName;
	}

	public void setGroupProductName(String groupProductName) {
		this.groupProductName = groupProductName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Collection<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductModel> products) {
		this.products = products;
	}
	
	
}
