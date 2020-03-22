package com.shop.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.shop.model.Product;

public class ProductDto {

	private int productId;
	private BigDecimal price;
	private String name;
	private UUID sku;
	private Date createdDate;

	public ProductDto() {
	}

	public ProductDto(Product product) {
		this.productId = product.getProductId();
		this.price = product.getPrice();
		this.name = product.getName();
		this.sku = product.getSku();
		this.createdDate = product.getCreatedDate();
	}

	public long getProductId() {
		return productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public UUID getSku() {
		return sku;
	}

	public void setSku(UUID sku) {
		this.sku = sku;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ProductDto(int id, BigDecimal price, String name, boolean isActive) {
		this.productId = id;
		this.price = price;
		this.name = name;
	}

}
