/*
 * Exercise 51 – CRUD H2
 *
 * Purpose:
 * Define the data received when creating a book.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex51.dto;

public record BookRequest(
    String title,
    String author,
    String theme,    
    int year,
    double price,
    String notes
) {}
