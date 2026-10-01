/*
 * Exercise 48 – Hide Internal Fields
 *
 * Purpose:
 * Define the public car data, excluding internal fields.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex48.dto;

public record CarResponse48 (
    int id, 
    String brand,
    String model,
    int year
) {}
