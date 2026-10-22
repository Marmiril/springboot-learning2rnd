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
        Product product01 = new Product("Monitor", "Electronics", 250.00, 10);

        repository.save(product);
        repository.save(product00);
        repository.save(product01);

        logger.info("Product saved successfully!");        
        
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

/*
        // Delete the product using its database-generated ID
        repository.deleteById(product00.getId());
        logger.info(
            "Product deleted. ID: {}",
            product00.getId()
        );
*/
        // Verify that the deleted product no longer exists in the database. existsById is inherited by JpaRepository
        boolean productExists = repository.existsById(product00.getId());
        logger.info(
            "Does the deleted product still exist? {}",
            productExists
        );

        // Counts the products remaining in the database
        long totalProducts = repository.count();
        logger.info("Total products in inventory: {}", totalProducts);

        // Search for a product without case sensitivity
        Optional<Product> productByName = repository.findByNameIgnoreCase("MOUSE");
        productByName.ifPresentOrElse(
            p -> logger.info("Product found by name: {}", p.getName()),
            () -> logger.warn("No product found with the specific name")
        );

        // Retrieve all produtcs belonging to the Electric category
        List<Product> electronicProducts = repository.findByCategoryIgnoreCase("ELECTRONICS");
        logger.info("Products found in Electronics: {}", electronicProducts.size());
        electronicProducts.forEach(p -> logger.info("Product in category Electronics: {}", p.getName()));

        // Retrieve products with a price lower than the specified amount
        List<Product> affordableProducts = repository.findByPriceLessThan(30.00);
        affordableProducts.forEach(p -> logger.info("Product: {}, price: {}", p.getName(), p.getPrice()));

        // Retrieve products between prices
        List<Product> productsInRange = repository.findByPriceBetween(20.00, 50.00);
        productsInRange.forEach(p -> logger.info("Product: {} - price: {}", p.getName(), p.getPrice()));

        // Retrieve products by category and max price
        List<Product> productsCategoryPrice = repository.findByCategoryIgnoreCaseAndPriceLessThan("Electronics", 100.00);
        productsCategoryPrice.forEach(p -> logger.info("Product: {} -- Price: {}", p.getName(), p.getPrice()));

        // Retrieve products with stock above a specified quantity
        List<Product> productsByStock = repository.findByStockGreaterThan(10);
        productsByStock.forEach(p -> logger.info("Product: {} -- Stock: {}", p.getName(), p.getStock()));

        // Retrieve products by price asc
        List<Product> productsCategoryOrderAsc = repository.findByCategoryIgnoreCaseOrderByPriceAsc("Electrinics");
        productsCategoryOrderAsc.forEach(p -> logger.info("Product: {} -- Price: {}", p.getName(), p.getPrice()));


    }

    // LOGGER
    private static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);    
}
