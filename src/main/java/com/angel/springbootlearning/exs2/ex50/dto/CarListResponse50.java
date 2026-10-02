/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Wrap a message and a list of public car DTOs.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.dto;

import java.util.List;

public record CarListResponse50(
    String message,
    List<CarResponse50> cars
) {}
