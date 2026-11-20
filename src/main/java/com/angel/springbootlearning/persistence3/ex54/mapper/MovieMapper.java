package com.angel.springbootlearning.persistence3.ex54.mapper;

import org.springframework.stereotype.Component;

import com.angel.springbootlearning.persistence3.ex54.dto.MovieDataResponse;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieRequest;
import com.angel.springbootlearning.persistence3.ex54.dto.MovieUpdate;
import com.angel.springbootlearning.persistence3.ex54.model.Movie;

@Component 
public class MovieMapper {

    public Movie toModel(MovieRequest request) {
        return new Movie(
            request.name().trim(),
            request.director().trim(),
            request.year(),
            request.stock(),
            request.value()
        );
    }

    public void toUpdate(Movie movie, MovieUpdate request) {
        movie.setStock(request.stock());
        movie.setValue(request.value());
    }

    public MovieDataResponse toResponse(Movie movie) {
        return new MovieDataResponse(
            movie.getId(),
            movie.getName(),
            movie.getDirector(),
            movie.getYear(),
            movie.getStock(),
            movie.getValue()
        );
    }
    
}
