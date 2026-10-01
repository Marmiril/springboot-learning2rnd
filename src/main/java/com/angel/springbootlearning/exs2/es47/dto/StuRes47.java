/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Define the student data returned after mapping.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.es47.dto;

public record StuRes47 (
    int id,
    String name,
    String role
) {}
