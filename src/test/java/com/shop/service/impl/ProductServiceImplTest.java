package com.shop.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;

@SpringBootTest
public class ProductServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@Mock
	private ProductServiceImpl productServiceImpl;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Autowired
	ModelMapper modelMapper;

	@Test
	public void getProduct() {
		int id = 1;
		Product product = new Product();
		product.setProductId(1);

		ProductDto productDto = new ProductDto();
		productDto.setProductId(1);
		Optional<Product> prod = Optional.of(product);
		
		given(productRepository.getProduct(Mockito.anyInt())).willReturn(prod);
		
		given(productServiceImpl.getProduct(Mockito.anyInt())).willReturn(productDto);
		assertThat(prod.get().getProductId()).isEqualTo(productDto.getProductId());
	}

	
}
