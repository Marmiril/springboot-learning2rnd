/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Wrap the message and the mapped student data.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.ex47.dto;

public record Res47 (
    String message,
    StuRes47 student
) {}
