package com.angel.springbootlearning.persistence2.ex53.dto;

public record ProductErrorResponse(
    int status,
    String message
) {}
