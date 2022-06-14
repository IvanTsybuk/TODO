package org.based.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.based.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    public ResponseEntity<ExceptionResponse> conflictException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }
}
