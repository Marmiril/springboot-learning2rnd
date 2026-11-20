package com.angel.springbootlearning.persistence3.ex54.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.angel.springbootlearning.persistence3.ex54.dto.MovieErrorResponse;

@RestControllerAdvice (basePackages = "com.angel.springbootlearning.persistence3.ex54")
public class GlobalHandler {
    
    @ExceptionHandler(InvalidMovie.class) 
    public ResponseEntity<MovieErrorResponse> handleInvalidMovie(InvalidMovie exception) { return buildError(HttpStatus.BAD_REQUEST, exception.getMessage()); }

    @ExceptionHandler(MovieNotFound.class)
    public ResponseEntity<MovieErrorResponse> handleMovieNotFound(MovieNotFound exception) { return buildError(HttpStatus.NOT_FOUND, exception.getMessage()); }

    @ExceptionHandler(DuplicateMovie.class)
    public ResponseEntity<MovieErrorResponse> handleDuplicateMovie(DuplicateMovie exception) { return buildError(HttpStatus.CONFLICT, exception.getMessage());}

    // Handles a malform JSON or values that cannot be deserialized
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MovieErrorResponse> handleInvalidJson(HttpMessageNotReadableException exception) { return buildError(HttpStatus.BAD_REQUEST, exception.getMessage()); }

    ///////////// PRIVATE MODES \\\\\\\\\\\\\\\\
    
    private ResponseEntity<MovieErrorResponse> buildError(HttpStatus status, String message) {
        MovieErrorResponse response = new MovieErrorResponse(status.value(), message);
        return ResponseEntity.status(status).body(response);

    }



}
