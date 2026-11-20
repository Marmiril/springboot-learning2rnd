package com.angel.springbootlearning.persistence3.ex54.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angel.springbootlearning.persistence3.ex54.model.Movie;

public interface MovieRepository extends JpaRepository <Movie, Long> {

    Optional<Movie>findByNameIgnoreCase(String name);

    List<Movie>findByDirectorIgnoreCase(String name);

    List<Movie>findByYear(Integer year);
    
    List<Movie>findByStock(Integer stock);

    List<Movie>findByValue(BigDecimal value);

    List<Movie>findByYearBetween(Integer minYear, Integer maxYear);

    List<Movie>findByValueBetween(BigDecimal minValue, BigDecimal maxValue);




}
