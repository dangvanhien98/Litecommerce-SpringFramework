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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Table(name = "Products")
@Entity(name = "ProductModel")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ProductID", columnDefinition = "int")
	private int productID;

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
	private Date entryDate;

	@Column(name = "Note", columnDefinition = "nvarchar(50)")
	private String note;
	
	@JsonIgnore
	@ManyToOne // có nhiều sản phẩm ở nhóm sản phẩm...n-1
	@JoinColumn(name = "GroupProductID") // thông qua khóa ngoại GroupProductID
	private GroupProductModel groupProduct;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<OrderDetailModel> orderDetails;

	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(int productID, String productName, int quantity, float price, float priceOld, float discount,
			String size, String color, String image, Date entryDate, String note, GroupProductModel groupProduct,
			Collection<OrderDetailModel> orderDetails) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.priceOld = priceOld;
		this.discount = discount;
		this.size = size;
		this.color = color;
		this.image = image;
		this.entryDate = entryDate;
		this.note = note;
		this.groupProduct = groupProduct;
		this.orderDetails = orderDetails;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(float priceOld) {
		this.priceOld = priceOld;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public GroupProductModel getGroupProduct() {
		return groupProduct;
	}

	public void setGroupProduct(GroupProductModel groupProduct) {
		this.groupProduct = groupProduct;
	}

	public Collection<OrderDetailModel> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetailModel> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}
