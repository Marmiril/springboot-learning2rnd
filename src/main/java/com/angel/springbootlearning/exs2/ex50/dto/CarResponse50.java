/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Define the public car data returned to the client.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.ex50.dto;

public record CarResponse50(
    int id,
    String brand,
    String model,
    int year
) {}
