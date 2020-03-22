package com.shop.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.dto.ProductDto;
import com.shop.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	ProductDto save(ProductDto productDto);

	@Query("select p from Product p where p.isActive=true")
	List<Product> getAll();	
	
	@Modifying
    @Query("update Product p set p.isActive = ?2 where p.id = ?1 and p.isActive=true")
	int deleteProduct(int id, boolean status);

	@Query("select p from Product p where p.isActive=true and p.id = ?1")
	Optional<Product> getProduct(int id);
	
}
