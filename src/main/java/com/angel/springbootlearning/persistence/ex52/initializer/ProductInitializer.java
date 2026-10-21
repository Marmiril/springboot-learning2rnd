/*
 * Exercise 52 - Product Initializer
 * Purpose: Insert an inventory product into H2 using Spring Data JPA.
 * URLs: Not available.
 */

package com.angel.springbootlearning.persistence.ex52.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.angel.springbootlearning.persistence.ex52.model.Product;
import com.angel.springbootlearning.persistence.ex52.repository.ProductRepository;

@Component 
public class ProductInitializer implements CommandLineRunner{
    
    private final ProductRepository repository;
    
    public ProductInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override 
    public void run(String... args) {
        // Persists a product when the applications starts.
        Product product = new Product("Mouse", "Electronics", 24.99, 20);

        repository.save(product);

        System.out.println("Product saved successfully!");
    }
}
