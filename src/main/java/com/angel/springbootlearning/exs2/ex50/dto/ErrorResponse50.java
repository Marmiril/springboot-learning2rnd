/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Define a consistent structure for HTTP error responses.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.dto;

public record ErrorResponse50(
    int status, // 400, 404 or 409
    String message
) {}
