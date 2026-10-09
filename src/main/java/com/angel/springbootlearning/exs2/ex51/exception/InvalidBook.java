package com.angel.springbootlearning.exs2.ex51.exception;

public class InvalidBook extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidBook(String message) {
         super(message);
    }
}
