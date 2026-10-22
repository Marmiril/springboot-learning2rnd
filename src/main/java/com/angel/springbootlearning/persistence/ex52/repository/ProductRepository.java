/*
 * Exercise 52 - Product Repository
 * Purpose: Access inventory products stored in H2 through Spring Data JPA.
 * URLs: Not available yet.
 */

package com.angel.springbootlearning.persistence.ex52.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.angel.springbootlearning.persistence.ex52.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{    

    /*
    JPA - Jakarta Persistence API
     */
    Optional<Product> findByNameIgnoreCase(String name);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Find products by category and max price
    List<Product> findByCategoryIgnoreCaseAndPriceLessThan(String category, double price);

    // Find products with stock above a specified quantity
    List<Product> findByStockGreaterThan(int stock);

    // Find products by category, ordered by asecending price
    List<Product> findByCategoryIgnoreCaseOrderByPriceAsc(String category);


    /*
    JPQL (Jakarta Persistence Query Language).
    */
    // Find products with stock below the specified quantity
    @Query ("SELECT p FROM Product p WHERE p.stock < :stock")
    List<Product> findProductsWithLowStock(@Param("stock") int stock);

    // Find products by category & max price, ordered by descending price
    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.category) = LOWER(:category)
        AND p.price <= :maxPrice
        ORDER BY p.price DESC
        """)
    List<Product> findProductsByCategoryAndMaxPrice(
        @Param("category") String category,
        @Param("maxPrice") double maxPrice
    );

}


