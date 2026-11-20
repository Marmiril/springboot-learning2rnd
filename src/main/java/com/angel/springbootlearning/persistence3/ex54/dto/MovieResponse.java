package com.angel.springbootlearning.persistence3.ex54.dto;

import com.angel.springbootlearning.persistence3.ex54.model.Movie;

public record MovieResponse(
    Movie movie,
    String message    
) {}
