package com.filesaver.domain.errors;

public class EntityAlreadyExistsError extends Exception {
  EntityAlreadyExistsError(String entityName) {
    super(entityName + " Already Exists.");
  }

}
