package com.angel.springbootlearning.exs2.ex51.dto;

public record BookResponse(
    int id,
    String title,
    String author,
    String theme,
    int year
) {}
