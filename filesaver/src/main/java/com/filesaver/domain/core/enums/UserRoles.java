package com.filesaver.domain.core.enums;

public enum UserRoles {
  ADM("ADM"),
  COMMON("COMMON");

  private String role;

  UserRoles(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
