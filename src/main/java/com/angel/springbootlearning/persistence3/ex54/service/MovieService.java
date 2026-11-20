package com.angel.springbootlearning.persistence3.ex54.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

import com.angel.springbootlearning.persistence2.ex53.dto.ListProductResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.ListMovieResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieFields;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieResponse;
import com.angel.springbootlearning.persistence3.ex54.mapper.MovieMapper;
import com.angel.springbootlearning.persistence3.ex54.repository.MovieRepository;

@Service 
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ListMovieResponse getAllMovies() {
        List<MovieResponse> responses = repository.findAll()
            .stream()
            .map(movie -> new MovieResponse(
                mapper.toResponse(movie),
                "Movie found successfully!"
            ))
            .toList();
        return new ListMovieResponse(responses);
    }

    public ListProductResponse searchProducts(
        String name,
        String director, 
        Integer year,
        Integer stock,
        BigDecimal value,
        Integer minYear,
        Integer maxYear,
        BigDecimal minValue, 
        BigDecimal maxValue
    ) {
        if(name != null)  
    }

    //////////////////////  PRIVATE METHODS //////////////////////
    
    private void validateFields(MovieFields request) {

    }

}
