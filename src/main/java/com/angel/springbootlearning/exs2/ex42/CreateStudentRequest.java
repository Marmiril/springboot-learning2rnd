/*
 * Exercise 42 - Input DTO
 *
 * Purpose:
 * Defines the data accepted when creating a student,
 * excluding fields assigned by the application.
 *
 * URLs:
 * None. This exercise does not expose HTTP endpoints.
 */

package com.angel.springbootlearning.exs2.ex42;

public record CreateStudentRequest (
    String name,
    String role) {}
