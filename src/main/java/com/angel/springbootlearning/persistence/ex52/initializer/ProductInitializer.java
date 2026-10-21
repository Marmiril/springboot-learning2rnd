/*
 * Exercise 52 - Product Initializer
 * Purpose: Insert an inventory product into H2 using Spring Data JPA.
 * URLs: Not available.
 */

package com.angel.springbootlearning.persistence.ex52.initializer;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.info("Product saved successfully!");

        repository.save(product);
        repository.save(product00);
        
        // Retrieve all products stored in H2
        List<Product> products = repository.findAll();
        products.forEach(p -> logger.info("Product found: {}", p.getName()));

        // Retrieve a product by its database-generated ID
        Optional<Product> foundProduct = repository.findById(product.getId());        
        foundProduct.ifPresent(p -> logger.info("Product found by ID: {}", p.getName()));

        // Check how JPA nadles a nonexistent product.
        Optional<Product> missingProduct = repository.findById(99999L);
        if (missingProduct.isEmpty()) { logger.info("Product with IS 9999 does not exist"); }
      
        // Update the product price and presist the changes
        product.setPrice(29.99);
        Product updatedProduct = repository.save(product);

        logger.info(
            "Product updated. ID: {}, new price: {}",
            updatedProduct.getId(),
            updatedProduct.getPrice()
        );

        // Update the product name and persis the changes
        product00.setName("Wireless keyboard");
        Product renamedProduct = repository.save(product00);
        logger.info(
            "Product renamed. ID: {}, new name: {}",
            renamedProduct.getId(),
            renamedProduct.getName()
        );
    }

    // LOGGER
    private static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);    
}
