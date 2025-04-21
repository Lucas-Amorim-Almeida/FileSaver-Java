package com.filesaver.domain.application.User.CreateUser;

import com.filesaver.domain.core.DTO.Password;
import com.filesaver.domain.core.DTO.Username;
import com.filesaver.domain.core.enums.UserRoles;
import com.filesaver.domain.core.enums.UserStatus;

public class CreateUserInputData {
  private Username username;
  private Password password;
  private UserRoles role;
  private final UserStatus status = UserStatus.ACTIVE;

  public CreateUserInputData(String username, String password, String role) throws Exception {
    this.username = new Username(username);
    this.password = new Password(password);
    this.role = UserRoles.valueOf(role);
  }
  public CreateUserInputData(String username, String password) throws Exception {
    this.username = new Username(username);
    this.password = new Password(password);
    this.role = UserRoles.COMMON;
  }


  public Username getUsername() {
    return username;
  }

  public Password getPassword() {
    return password;
  }

  public UserRoles getRole() {
    return role;
  }

  public UserStatus getStatus() {
    return status;
  }
}
