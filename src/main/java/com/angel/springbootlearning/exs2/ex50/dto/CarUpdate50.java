/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Define the editable car fields for full and partial updates.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.ex50.dto;

public record CarUpdate50(
    double price,
    String notes
) {}
