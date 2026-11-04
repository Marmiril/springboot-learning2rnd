package com.angel.springbootlearning.persistence2.ex53.exception;

public class InvalidProduct extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidProduct(String message) { super(message); }
}
