package com.angel.springbootlearning.persistence3.ex54.dto;

import java.util.List;


public record ListMovieResponse(
    List<MovieResponse> movies
) {}
