/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Report invalid car request data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.exception;

public class InvalidCar50 extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public InvalidCar50(String message) {
        super(message);
    }
}
