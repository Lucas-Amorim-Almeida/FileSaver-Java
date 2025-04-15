package com.filesaver.domain.errors;

public class EntityAlreadyExistsError extends Exception {
  public EntityAlreadyExistsError(String entityName) {
    super(entityName + " Already Exists.");
  }

}
