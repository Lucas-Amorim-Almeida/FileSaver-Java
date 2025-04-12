package com.filesaver.domain.errors;

public class EntityNotFoundError extends Exception {
  EntityNotFoundError(String entityName) {
    super(entityName + " not found.");
  }
}
