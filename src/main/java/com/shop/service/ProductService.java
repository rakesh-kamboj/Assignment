package com.shop.service;

import java.util.List;

import com.shop.dto.ProductDto;
import com.shop.model.Product;

public interface ProductService {

	ProductDto getProduct(int id);

	List<ProductDto> getAllProduct();

	ProductDto updateProduct(Product productDto);

	void deleteProduct(int id);

	ProductDto createProduct(Product productDto);
}
