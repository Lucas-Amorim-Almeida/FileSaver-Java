package com.filesaver.domain.errors;

public class EntityNotFoundError extends Exception {
  public EntityNotFoundError(String entityName) {
    super(entityName + " not found.");
  }
}
