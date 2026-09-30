/*
 * Exercise 44 - Update DTO
 *
 * Purpose:
 * Defines the editable fields received when updating
 * an existing student.
 *
 * URLs:
 * None. This exercise does not expose HTTP endpoints.
 */

package com.angel.springbootlearning.exs2.ex44;

public record UpdateStudentRequest (
        String name,
    String role) { }
