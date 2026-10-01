/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Define the public car data returned to the client.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49.dto;

public record CarResponse49 (
    int id, 
    String brand,
    String model,
    int year
) {}
