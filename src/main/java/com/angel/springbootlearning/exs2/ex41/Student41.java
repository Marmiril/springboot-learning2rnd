/*
 * Exercise 41 - Domain Model
 *
 * Purpose:
 * Defines the internal domain model used to represent
 * a student inside the application.
 *
 * URLs:
 * None. This exercise does not expose HTTP endpoints.
 */

package com.angel.springbootlearning.exs2.ex41;

import java.time.LocalDateTime;

public record Student41 (
    int id,
    String name,
    String role, 
    LocalDateTime regDate
) {}
