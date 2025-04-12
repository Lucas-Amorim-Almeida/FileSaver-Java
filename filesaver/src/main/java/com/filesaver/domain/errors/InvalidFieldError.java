package com.filesaver.domain.errors;

public class InvalidFieldError extends Error {
  public InvalidFieldError(String fieldName) {
    super(fieldName + " is not valid.");
  }
}
