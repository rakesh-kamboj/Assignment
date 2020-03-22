package com.shop.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
	String email;
	List<ProductOrder> productOrder;
	BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}

}