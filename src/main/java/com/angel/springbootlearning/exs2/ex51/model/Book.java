package com.angel.springbootlearning.exs2.ex51.model;

public record Book (
    int id,
    String title, 
    String author,
    int year,
    double price,
    String theme
) {}
