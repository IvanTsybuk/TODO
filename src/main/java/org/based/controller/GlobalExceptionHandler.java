package org.based.controller;

import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(EntityNotFoundException e) {
        log.debug("GlobalExceptionHandler: notFoundException");
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(),
                LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> conflictException(EntityAlreadyExistsException e) {
        log.debug("GlobalExceptionHandler: conflictException");
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(),
                LocalDateTime.now()), HttpStatus.CONFLICT);
    }
}
