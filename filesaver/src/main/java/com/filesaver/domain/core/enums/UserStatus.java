package com.filesaver.domain.core.enums;

public enum UserStatus {
  ACTIVE("ACTIVE"),
  DELETED("DELETED");

  private String status;

  UserStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
