/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Convert custom exceptions into consistent HTTP error responses.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.angel.springbootlearning.exs2.ex50.dto.ErrorResponse50;

@RestControllerAdvice(basePackages = "com.angel,springbootlearning-exs2.ex50")
public class GlobalHandler50 {

    @ExceptionHandler(InvalidCar50.class)
    public ResponseEntity<ErrorResponse50> handleInvalidCar(InvalidCar50 exception) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage()); 
    }

    @ExceptionHandler (CarNotFound50.class)
    public ResponseEntity<ErrorResponse50> handleCarNotFound(CarNotFound50 exception) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler (DuplicateCar50.class)
    public ResponseEntity<ErrorResponse50> handleDuplicateCar(DuplicateCar50 exception) {
        return buildError(HttpStatus.CONFLICT, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse50> buildError(
        HttpStatus status,
        String message
    ) { 
        return ResponseEntity.status(status).body(new ErrorResponse50(status.value(), message));
    }
    
    
}
