package com.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.model.Order;
import com.shop.model.OrdersDetails;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@SuppressWarnings("unchecked")
	Order save(Order o);

	@Query("select od from Order od where createdDate >= ?1 and createdDate <= ?2")
	List<Order> getOrder(Date fromDate, Date toDate);

	@Query("select od from OrdersDetails od where od.productId = ?1")	
	List<OrdersDetails> countOrderQunatity(int productId);
}
