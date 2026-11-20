package com.angel.springbootlearning.persistence3.ex54.exception;

public class MovieNotFoundByDirectorOrStock extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MovieNotFoundByDirectorOrStock(String director) { super ("There is no movie with director: " + director); }
    public MovieNotFoundByDirectorOrStock(Integer stock) { super ("There is no movie with such stock: " + stock); }
}
