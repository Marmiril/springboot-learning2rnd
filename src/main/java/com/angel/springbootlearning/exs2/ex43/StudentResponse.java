/*
 * Exercise 43 - Output DTO
 *
 * Purpose:
 * Defines the student data returned to the client,
 * independently of the internal domain model.
 *
 * URLs:
 * None. This exercise does not expose HTTP endpoints.
 */

package com.angel.springbootlearning.exs2.ex43;

public record StudentResponse (
    int id,
    String name,
    String role
) {
    
}
