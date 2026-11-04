package com.angel.springbootlearning.persistence2.ex53.dto;

public record ProductResponse(
    ProductDataResponse response,
    String message
) {}
