package com.filesaver.domain.errors;

public class InternalError extends Exception {
  InternalError() {
    super("An internal error occurred.");
  }
}
