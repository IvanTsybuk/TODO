package org.based.controller;

import java.time.LocalDateTime;
import org.based.exceptions.EntityAlreadyExistsException;
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
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> conflictException(EntityAlreadyExistsException e,
                                                               WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), request,
                LocalDateTime.now()), HttpStatus.CONFLICT);
    }
}
