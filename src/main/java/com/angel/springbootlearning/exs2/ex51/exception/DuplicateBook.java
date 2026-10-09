package com.angel.springbootlearning.exs2.ex51.exception;

public class DuplicateBook extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    
    public DuplicateBook(String message) { super(message);}
    
}
