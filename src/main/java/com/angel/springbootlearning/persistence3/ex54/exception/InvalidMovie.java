package com.angel.springbootlearning.persistence3.ex54.exception;

public class InvalidMovie extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidMovie(String message) { super(message); }

}
