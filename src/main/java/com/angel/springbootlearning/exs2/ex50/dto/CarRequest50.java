/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Define the data received when creating a car.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.dto;

public record CarRequest50(
    String brand,
    String model,
    int year,
    double price,
    String notes
) {}
