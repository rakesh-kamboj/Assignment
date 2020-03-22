package com.shop.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	private static final Log LOG = LogFactory.getLog(ProductController.class);

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/getProduct/{id}", produces = "application/json")
	@ApiOperation(value = "Find Product based on product id", notes = "Return product data", response = ProductDto.class)
	public ResponseEntity<?> getProduct(@PathVariable int id) {
		ProductDto productDto = productService.getProduct(id);
		return ok(productDto);
	}

	/**
	 * @return
	 */
	@GetMapping(path = "/getAllProduct", produces = "application/json")
	@ApiOperation(value = "Find all Product", notes = "Return all product data", response = ProductDto.class)
	public ResponseEntity<?> getAllProduct() {
		List<ProductDto> productList = productService.getAllProduct();
		return ok(productList);
	}

	/**
	 * @param productDto
	 * @return
	 */
	@PostMapping(path = "/createProduct", produces = "application/json")
	@ApiOperation(value = "Create Product", notes = "Return created data", response = ProductDto.class)
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody Product productDto) {
		ProductDto product = productService.createProduct(productDto);
		return ok(product);
	}

	/**
	 * @param productDto
	 * @param id
	 * @return
	 */
	@PutMapping(path = "/updateProduct/{id}", produces = "application/json")
	@ApiOperation(value = "Update Product based on product id", notes = "Return updated data", response = ProductDto.class)
	public ResponseEntity<ProductDto> updateProduct(@RequestBody Product productDto, @PathVariable int id) {
		productDto.setProductId(id);
		ProductDto product = productService.updateProduct(productDto);
		return ok(product);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/deleteProduct/{id}", produces = "application/json")
	@ApiOperation(value = "Delete Product", notes = "Return deletion status")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		Map<String, String> map = new HashMap<String, String>();
		productService.deleteProduct(id);

		map.put("message", "Product is deleted.");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}
}
