package com.angel.springbootlearning.persistence3.ex54.exception;

import java.math.BigDecimal;

public class MovieNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MovieNotFound(Long id) { super("There is no movie with id: " + id); }
    public MovieNotFound(String name) { super("There is no movie with such title: " + name); }
    public MovieNotFound(Integer year) { super("There is no movie from year: " + year); }
    public MovieNotFound(BigDecimal value) { super("There is no movie with such value: " + value); }    
}
