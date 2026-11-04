package com.angel.springbootlearning.persistence2.ex53.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.angel.springbootlearning.persistence2.ex53.dto.ProductErrorResponse;

@RestControllerAdvice (basePackages = "com.angel.springbootlearning.persistence2.ex53")
public class GlobalHandler53 {

    @ExceptionHandler(InvalidProduct.class)
    public ResponseEntity<ProductErrorResponse> handleInvalidProduct(InvalidProduct exception) { return buildError(HttpStatus.BAD_REQUEST, exception.getMessage());    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ProductErrorResponse> handleProductNotFound(ProductNotFound exception) { return buildError(HttpStatus.NOT_FOUND, exception.getMessage()); }

    @ExceptionHandler(DuplicateProduct.class)
    public ResponseEntity<ProductErrorResponse> handleDuplicateProduct(DuplicateProduct exception) { return buildError(HttpStatus.CONFLICT, exception.getMessage()); }

    // Handles a malforme JSON or values that cannot be deserialized
    @ExceptionHandler (HttpMessageNotReadableException.class)
    public ResponseEntity<ProductErrorResponse> handleInvalidJson(HttpMessageNotReadableException exception) { return buildError(HttpStatus.BAD_REQUEST, exception.getMessage()); }

    private ResponseEntity<ProductErrorResponse> buildError(HttpStatus status, String message) {
        ProductErrorResponse response = new ProductErrorResponse(status.value(), message);
        return ResponseEntity.status(status).body(response);
    }
}
