package com.angel.springbootlearning.persistence3.ex54.exception;

public class DuplicateMovie extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateMovie(String message) { super(message); }
    
}
