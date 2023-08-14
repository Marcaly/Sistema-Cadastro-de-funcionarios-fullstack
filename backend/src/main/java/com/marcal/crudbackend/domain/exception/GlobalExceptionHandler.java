package com.marcal.crudbackend.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateEmployeeException.class)
  public ResponseEntity<String> handleDuplicateEmployeeException(DuplicateEmployeeException exc) {
    return ResponseEntity.badRequest().body(exc.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exc) {
    return ResponseEntity.badRequest().body(exc.getMessage());
  }

}
