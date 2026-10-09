package com.angel.springbootlearning.exs2.ex51.dto;

public record BookDataResponse(
    int id,
    String title,
    String author,
    String theme,
    int year
) {}
