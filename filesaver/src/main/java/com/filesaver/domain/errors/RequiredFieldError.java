package com.filesaver.domain.errors;

public class RequiredFieldError extends Exception {
  public RequiredFieldError(String fieldName ) {
    super(fieldName + " is required.");
  }
}
