/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Define the data required to create a product.
 * URLs: POST /exercise53/products
 */

package com.angel.springbootlearning.persistence2.ex53.dto;

import java.math.BigDecimal;

public record ProductRequest53(
    String name,
    BigDecimal price,
    Integer stock
) {}
