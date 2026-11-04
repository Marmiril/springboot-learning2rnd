/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Provide database access for Product53 using Spring Data JPA.
 * URLs: None. Internal repository component.
 */

package com.angel.springbootlearning.persistence2.ex53.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angel.springbootlearning.persistence2.ex53.model.Product53;

public interface ProductRepository53 extends JpaRepository<Product53, Long> {

    Optional<Product53>findByNameIgnoreCase(String name);
}
