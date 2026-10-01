/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Define the complete internal car model.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49.model;

public record Car49 (
    int id,
    String brand,
    String model,
    int year,
    double price,
    String notes
) {}
