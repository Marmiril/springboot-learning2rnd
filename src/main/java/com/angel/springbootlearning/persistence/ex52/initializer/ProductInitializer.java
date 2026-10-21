/*
 * Exercise 52 - Product Initializer
 * Purpose: Insert an inventory product into H2 using Spring Data JPA.
 * URLs: Not available.
 */

package com.angel.springbootlearning.persistence.ex52.initializer;

import java.util.List;
import java.util.Optional;

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
        Product product00 = new Product("Keyboard", "Electronics", 49.99, 10);

        repository.save(product);
        repository.save(product00);
        
        // Retrieve all products stored in H2
        List<Product> products = repository.findAll();
        products.forEach(p ->
            System.out.println("Product found: " + p.getName())
        );

        // Retrieve a product by its database-generated ID
        Optional<Product> foundProduct = repository.findById(product.getId());
        
        foundProduct.ifPresent(p -> System.out.println("Product found by ID: " + p.getName()));

        System.out.println("Product saved successfully!");
    }
}
