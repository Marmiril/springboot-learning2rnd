/*
 * Exercise 51 - Introduction to Persistence
 *
 * Purpose:
 * Define the book domain model for a complete
 * in-memory CRUD before database integration.
 *
 * URLs:
 * http://localhost:8080/exercise51/books
 * http://localhost:8080/exercise51/books/{id}
 */

package com.angel.springbootlearning.exs2.ex51.model;

public record Book (
    int id,
    String title, 
    String author,
    BookTheme theme,
    int year,
    double price,
    String notes
) {}
