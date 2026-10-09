
/*
 * Exercise 51 - Introduction to Persistence
 *
 * Purpose:
 * Handle custom book exceptions and return
 * consistent HTTP error responses.
 *
 * URLs:
 * http://localhost:8080/exercise51/books
 */

package com.angel.springbootlearning.exs2.ex51.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.angel.springbootlearning.exs2.ex51.dto.BookErrorResponse;

@RestControllerAdvice(basePackages = "com.angel.springbootlearning.exs2.ex51")
public class GlobalHandler51 {

    @ExceptionHandler(InvalidBook.class) 
    public ResponseEntity<BookErrorResponse> handleInvalidBook(InvalidBook exception) { return buildError(HttpStatus.BAD_REQUEST, exception.getMessage()); }

    @ExceptionHandler(BookNotFound.class) {
    public ResponseEntity<BookErrorResponse> handleBookNotFound(BookNotFound exception) { return buildError(HttpStatus.NOT_FOUND, exception.getMessage()); }

    @ExceptionHandler(DuplicateBook.class)
    public ResponseEntity<BookErrorResponse> handleDuplicateBook(DuplicateBook exception) { return buildError(HttpStatus.CONFLICT, exception.getMessage()); }
    
    private ResponseEntity<BookErrorResponse> buildError(
        HttpStatus status,
        String message
    ) {
        BookErrorResponse response = new BookErrorResponse(
            status.value(),
            message
        );
        return ResponseEntity.status(status).body(response);
    }
    

}
