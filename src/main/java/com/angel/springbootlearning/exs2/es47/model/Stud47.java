/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Define the internal student model.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.es47.model;

import java.time.LocalDateTime;

public record Student47 (   
    int id,
    String name,
    String role, 
    LocalDateTime regDate
){} 
