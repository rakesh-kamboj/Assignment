package com.shop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ORDERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdDate" }, allowGetters = true)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "EMAIL")
	private String email;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date createdDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_ORDERS_DETAILS", joinColumns = {
			@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ORDERS_DETAILS_ID", referencedColumnName = "ID") })
	private List<OrdersDetails> ordersDetails;

	public int getid() {
		return id;
	}

	public void setOrderId(int orderId) {
		this.id = orderId;
	}

	public List<OrdersDetails> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrdersDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Order() {
	}

	public String toString() {
		return "";
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
