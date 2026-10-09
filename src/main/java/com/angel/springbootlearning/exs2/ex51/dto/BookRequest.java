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

import com.angel.springbootlearning.exs2.ex51.model.BookTheme;

public record BookRequest(
    String title,
    String author,
    BookTheme theme,    
    int year,
    double price,
    String notes
) {}
