package com.angel.springbootlearning.persistence3.ex54.dto;

public record MovieErrorResponse(
    int status, 
    String message
) {}
