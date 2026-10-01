/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Define the data received when creating a car.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.ex49.dto;

public record carRequest49 (
    int id, 
    String brand,
    String model,
    int year,
    double price,
    String notes
) {}
