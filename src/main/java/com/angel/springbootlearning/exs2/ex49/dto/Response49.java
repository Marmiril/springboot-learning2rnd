/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Wrap a message and the public car data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49.dto;

public record Response49 (
    String message,
    CarResponse49 car
) {}
