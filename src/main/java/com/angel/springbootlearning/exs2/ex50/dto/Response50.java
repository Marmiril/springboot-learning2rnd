/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Wrap a message and the public car data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.dto;

public record Response50(
    String message,
    CarResponse50 car
) {}
