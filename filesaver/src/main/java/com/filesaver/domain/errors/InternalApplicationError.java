package com.filesaver.domain.errors;

public class InternalApplicationError extends Exception {
  public InternalApplicationError() {
    super("An internal error occurred.");
  }
}
