package com.shop.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shop.common.IntegrationTestUtil;
import com.shop.dto.OrderDto;
import com.shop.model.Order;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderControllerTest {

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	OrderService orderService;

	@MockBean
	ProductRepository productRepository;
	
    private MockMvc mockMvc;
    
    @Autowired
    public WebApplicationContext context;
	
	@Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	
	@Test
	public void getAllProduct() throws Exception {
		Order order = new Order();
		OrderDto orderDto = new OrderDto();
		Mockito.when(orderService.save(orderDto)).thenReturn(order);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/createOrder")
										.contentType(MediaType.APPLICATION_JSON)
										.content(IntegrationTestUtil.convertObjectToJsonBytes(order));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void getProduct() throws Exception {
	
		Order order = new Order();
		Map map = new HashMap();
		Mockito.when(orderService.getOrder(new Date(), new Date())).thenReturn(new HashMap());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getOrder?fromDate=2020-03-22&toDate=2020-03-22")
										.contentType(MediaType.APPLICATION_JSON)
										.content(IntegrationTestUtil.convertObjectToJsonBytes(map));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	
	
}
