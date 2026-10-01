/*
 * Exercise 48 – Hide Internal Fields
 *
 * Purpose:
 * Wrap a message and the public car data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex48.dto;

public record Res48 (
    String message,
    CarResponse48 car
) {}
