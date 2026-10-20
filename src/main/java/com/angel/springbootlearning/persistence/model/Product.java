/*
 * Exercise 52 - Product Entity
 * Purpose: Represent inventory products as persistent JPA entities.
 * URLs: Not available yet.
 */

package com.angel.springbootlearning.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Product {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;
    private int stock;

    protected Product() {
        // Required by JPA to instantiate entities.
    }
    
}
