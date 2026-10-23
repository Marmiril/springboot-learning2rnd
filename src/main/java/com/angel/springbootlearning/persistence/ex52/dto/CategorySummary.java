/*
 * Exercise 52 - Category Summary
 * Purpose: Represent aggregated inventory values by category.
 * URLs: Not available.
 */

package com.angel.springbootlearning.persistence.ex52.dto;

public record CategorySummary(
    String category,
    Double totalValue
) {}
