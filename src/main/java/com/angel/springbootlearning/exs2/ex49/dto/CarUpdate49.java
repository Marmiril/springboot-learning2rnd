/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Define the editable data received when updating a car.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49.dto;

public record CarUpdate49 (
    double price,
    String notes
){}
