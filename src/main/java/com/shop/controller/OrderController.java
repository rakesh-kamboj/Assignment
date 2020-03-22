package com.shop.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.OrderDto;
import com.shop.model.Order;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderService orderService;

	@Autowired
	ProductRepository productRepository;

	private static final Log LOG = LogFactory.getLog(OrderController.class);

	/**
	 * @param orderDto
	 * @return
	 */
	@PostMapping(path = "/createOrder", produces = "application/json")
	@ApiOperation(value = "Create Order", notes = "Return created order", response = Order.class)
	public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
		Order order = orderService.save(orderDto);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	/**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@GetMapping(path = "/getOrder", produces = "application/json")
	@ApiOperation(value = "Find all Order", notes = "Return all orders")
	public ResponseEntity<?> getOrder(@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd")  Date fromDate, @RequestParam("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		Map orderList = orderService.getOrder(fromDate, toDate);		
		return ok(orderList);
	}
}
