package com.angel.springbootlearning.exs2.ex51.exception;

public class BookNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookNotFound(int id) { super("There is no book with id: " + id); }
    public BookNotFound(String message) { super(message); }
}
