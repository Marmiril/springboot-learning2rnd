/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Report that the requested car was not found.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.exception;

public class CarNotFound50 extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CarNotFound50(String message) {
        super(message);
    }    
}
