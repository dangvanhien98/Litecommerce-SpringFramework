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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "Products")
@Entity(name = "ProductModel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ProductID", columnDefinition = "int")
	private Integer productID;

	@Column(name = "ProductName", columnDefinition = "nvarchar(50)")
	private String productName;

	@Column(name = "Quantity", columnDefinition = "int")
	private int quantity;

	@Column(name = "Price", columnDefinition = "float")
	private float price;

	@Column(name = "PriceOld", columnDefinition = "float")
	private float priceOld;

	@Column(name = "Discount", columnDefinition = "float")
	private float discount;

	@Column(name = "Size", columnDefinition = "nvarchar(50)")
	private String size;

	@Column(name = "Color", columnDefinition = "nvarchar(50)")
	private String color;

	@Column(name = "Image", columnDefinition = "nvarchar(50)")
	private String image;

	@Column(name = "EntryDate", columnDefinition = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;

	@Column(name = "Note", columnDefinition = "nvarchar(50)")
	private String note;
	
	@JsonIgnore
	@ManyToOne// có nhiều sản phẩm ở nhóm sản phẩm...n-1
	@JoinColumn(name = "GroupProductID") // thông qua khóa ngoại GroupProductID
	private GroupProductModel groupProduct;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<OrderDetailModel> orderDetails;
}
