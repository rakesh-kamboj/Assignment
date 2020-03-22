package com.shop.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductControllerTest {

	@MockBean
	ProductService productService;
	
    private MockMvc mockMvc;
    
    @Autowired
    public WebApplicationContext context;
	
	@Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	
	@Test
	public void getAllProduct() throws Exception {
		List<ProductDto> productList = new ArrayList<ProductDto>();
		Mockito.when(productService.getAllProduct()).thenReturn(productList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getAllProduct")
										.contentType(MediaType.APPLICATION_JSON)
										.content(IntegrationTestUtil.convertObjectToJsonBytes(productList));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void getProduct() throws Exception {
		ProductDto product = new ProductDto();
		Mockito.when(productService.getProduct(Mockito.anyInt())).thenReturn(product);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getProduct/1")
										.contentType(MediaType.APPLICATION_JSON)
										.content(IntegrationTestUtil.convertObjectToJsonBytes(product));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	
	@Test
	public void createProduct() throws Exception {
		Product product = new Product();
		ProductDto productDto = new ProductDto();
		Mockito.when(productService.createProduct(product)).thenReturn(productDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/createProduct")
										.contentType(MediaType.APPLICATION_JSON)
										.content(IntegrationTestUtil.convertObjectToJsonBytes(productDto))
										.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	
	@Test
	public void updateProduct() throws Exception {
		ProductDto productDto = new ProductDto();
		Product product = new Product();
		Mockito.when(productService.updateProduct(product)).thenReturn(productDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateProduct/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(IntegrationTestUtil.convertObjectToJsonBytes(productDto))
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteProduct() throws Exception {
		ProductDto productDto = new ProductDto();
		Product product = new Product();
		Mockito.when(productService.updateProduct(product)).thenReturn(productDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteProduct/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(IntegrationTestUtil.convertObjectToJsonBytes(productDto));
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	
}
