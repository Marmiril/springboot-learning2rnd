/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Define the complete internal car model for the layered CRUD.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.model;

public record Car50(
    int id,
    String brand,
    String model,
    int year,
    double price,
    String notes
) {}
