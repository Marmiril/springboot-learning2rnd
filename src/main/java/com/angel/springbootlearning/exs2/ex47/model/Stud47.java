/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Define the internal student model.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex47.model;

import java.time.LocalDateTime;

public record Stud47 (   
    int id,
    String name,
    String role, 
    LocalDateTime regDate
){} 
