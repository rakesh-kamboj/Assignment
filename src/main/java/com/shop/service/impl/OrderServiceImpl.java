package com.shop.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.OrderDto;
import com.shop.dto.ProductOrder;
import com.shop.exception.RecordNotFoundException;
import com.shop.model.Order;
import com.shop.model.OrdersDetails;
import com.shop.model.Product;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Order save(OrderDto orderDto) {

		List<OrdersDetails> orderDetailsList = new ArrayList<OrdersDetails>();
		Order order = new Order();
		order.setEmail(orderDto.getEmail());
		List<ProductOrder> list = orderDto.getProductOrder();

		for (ProductOrder productOrder : list) {
			OrdersDetails orderDetails = new OrdersDetails();
			Product product = productRepository.getOne(productOrder.getId());

			List<OrdersDetails> ordersDetails = orderRepository.countOrderQunatity(productOrder.getId());
			int qunatityCount = ordersDetails.stream().mapToInt(OrdersDetails::getQuantity).sum();

			if (product != null && qunatityCount < product.getProductQuantity()) {
				orderDetails.setProductId(product.getProductId());
				orderDetails.setPrice(product.getPrice());
				orderDetails.setQuantity(productOrder.getQuantity());
				orderDetailsList.add(orderDetails);
			} else {
				throw new RecordNotFoundException("No Product were found");
			}
		}
		order.setOrdersDetails(orderDetailsList);
		Order createdOrder = orderRepository.save(order);

		return createdOrder;
	}

	@Override
	public Map<String, Object> getOrder(Date fromDate, Date toDate) {
		List<Order> list = orderRepository.getOrder(fromDate, toDate);
		Map<String, Object> map = getOrderData(list);

		if (list.isEmpty()) {
			throw new RecordNotFoundException("No Order were found");
		}
		return map;
	}

	private Map<String, Object> getOrderData(List<Order> list) {		
		Map<String, Object> map = new HashMap<String, Object>();		
		List<Object> outerList=new ArrayList<>();
		Map<Object, Object> mapOrder=new HashMap<>();		
		int totalPrice = 0;
		
		for(Order order:list) {			
			for(OrdersDetails orderDetails : order.getOrdersDetails()) {
				totalPrice  += Integer.valueOf(orderDetails.getPrice().intValue()) * Integer.valueOf(orderDetails.getQuantity());
			}

			mapOrder=new HashMap<>();
			mapOrder.put("order_"+order.getid(), order);
			mapOrder.put("totalPrice", totalPrice);
			outerList.add(mapOrder);
			totalPrice = 0;
		}
		map.put("data", outerList);
		return map;
	}
}