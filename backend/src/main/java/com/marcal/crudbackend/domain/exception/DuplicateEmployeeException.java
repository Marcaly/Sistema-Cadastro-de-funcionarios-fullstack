package com.marcal.crudbackend.domain.exception;

public class DuplicateEmployeeException extends RuntimeException {

  public DuplicateEmployeeException(String msg) {
    super(msg);
  }

}
