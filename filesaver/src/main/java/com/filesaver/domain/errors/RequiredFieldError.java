package com.filesaver.domain.errors;

public class RequiredFieldError extends Exception {
  RequiredFieldError(String fieldName ) {
    super(fieldName + " is required.");
  }
}
