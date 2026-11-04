/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Define a JPA entity representing an inventory product.
 * URLs: To be defined.
 */


package com.angel.springbootlearning.persistence2.ex53.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="product53")
public class Product53 {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String name;
    private BigDecimal price;
    private Integer stock;

    public Product53() {
    }

    public Product53(String name, BigDecimal price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStock(Integer stock) { this.stock = stock; }

    
}
