/*
 * Exercise 48 – Hide Internal Fields
 *
 * Purpose:
 * Define a car model containing public and internal data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex48.model;

public record Car48 (
    int id,
    String brand,
    String model,
    int year,
    double price,
    String notes
) {}
