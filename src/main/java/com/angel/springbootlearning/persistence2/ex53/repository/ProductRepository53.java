/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Provide database access for Product53 using Spring Data JPA.
 * URLs: None. Internal repository component.
 */

package com.angel.springbootlearning.persistence2.ex53.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angel.springbootlearning.persistence2.ex53.model.Product53;

public interface ProductRepository53 extends JpaRepository<Product53, Long> {

    Optional<Product53>findByNameIgnoreCase(String name);

    List<Product53>findByStock(Integer stock);

    List<Product53>findByPrice(BigDecimal price);

    List<Product53>findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
