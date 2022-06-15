package org.based.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.based.exceptions.EntityConstraintException;
import org.based.exceptions.EntityNotFoundException;
import org.based.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(EntityNotFoundException e,
                                                               WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), request,
                LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityConstraintException.class)
    public ResponseEntity<ExceptionResponse> conflictException(EntityConstraintException e,
                                                               WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), request,
                LocalDateTime.now()), HttpStatus.CONFLICT);
    }
}
