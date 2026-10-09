package com.angel.springbootlearning.exs2.ex51.dto;

import com.angel.springbootlearning.exs2.ex51.model.BookTheme;

public record BookDataResponse(
    int id,
    String title,
    String author,
    BookTheme theme,
    int year
) {}
