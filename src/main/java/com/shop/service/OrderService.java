package com.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shop.dto.OrderDto;
import com.shop.model.Order;

public interface OrderService {

	Order save(OrderDto orderDto);

	Map getOrder(Date fromDate, Date toDate);
}
