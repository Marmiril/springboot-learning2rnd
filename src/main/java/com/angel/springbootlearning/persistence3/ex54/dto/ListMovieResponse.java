package com.angel.springbootlearning.persistence3.ex54.dto;

import java.util.List;

import com.angel.springbootlearning.persistence3.ex54.model.Movie;

public record ListMovieResponse(
    List<Movie> movies,
    String message
) {}
