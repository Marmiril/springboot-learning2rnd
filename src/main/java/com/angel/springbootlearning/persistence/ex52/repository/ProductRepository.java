/*
 * Exercise 52 - Product Repository
 * Purpose: Access inventory products stored in H2 through Spring Data JPA.
 * URLs: Not available yet.
 */

package com.angel.springbootlearning.persistence.ex52.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angel.springbootlearning.persistence.ex52.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{    

    Optional<Product> findByNameIgnoreCase(String name);

}


