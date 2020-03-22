package com.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.ProductDto;
import com.shop.exception.RecordNotFoundException;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ModelMapper modelMapper;

	public static final int productQuantity = 10;

	@Override
	public ProductDto getProduct(int id) {
		Optional<Product> product = productRepository.getProduct(id);
		ProductDto productDto = product.map(ProductDto::new).get();
		if(productDto == null) {
				throw new RecordNotFoundException("No Product were found");
		}
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> products = productRepository.getAll();
		List<ProductDto> productDto = products.parallelStream().map(product -> new ProductDto(product)).collect(Collectors.toList());
		if(productDto.isEmpty()) {
			throw new RecordNotFoundException("No Products were found");
		}
		return productDto;
	}

	@Override
	public ProductDto createProduct(Product product) {
		product.setSku(UUID.randomUUID());
		product.setProductQuantity(productQuantity);
		Product createdProduct = productRepository.save(product);
		ProductDto productDto = modelMapper.map(createdProduct, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto updateProduct(Product product) {
		ProductDto productDto = null;
		Optional<Product> productD = productRepository.getProduct(product.getProductId());
		if (Optional.ofNullable(productD) != null) {

			Product productData = productD.get();

			if (product.getName() != null) {
				productData.setName(product.getName());
			}
			if (product.getPrice() != productData.getPrice()) {
				productData.setPrice(product.getPrice());
			}

			Product updateProduct = productRepository.save(productData);
			productDto = modelMapper.map(updateProduct, ProductDto.class);
		} 
		return productDto;
	}

	@Override
	public void deleteProduct(int id) {
		boolean status = false;
		int value = productRepository.deleteProduct(id, status);
		if(value == 0) {
			throw new RecordNotFoundException("No Product were found");
		}
	}

}
