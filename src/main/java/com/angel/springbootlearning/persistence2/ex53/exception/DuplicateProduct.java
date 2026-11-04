package com.angel.springbootlearning.persistence2.ex53.exception;

public class DuplicateProduct extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateProduct(String message) { super(message); }
    
}
