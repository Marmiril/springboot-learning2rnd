/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Report a car that conflicts with an existing entry.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.exception;

public class DuplicateCar50 extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public DuplicateCar50(String message) {
        super(message);
    }
}
