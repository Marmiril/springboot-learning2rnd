package com.angel.springbootlearning.exs2.ex51.dto;

import java.util.List;

public record BookListResponse(
    String message,
    List<BookResponse> books
) {}
