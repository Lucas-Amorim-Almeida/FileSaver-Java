package com.filesaver.domain.core;

import com.filesaver.domain.core.DTO.Password;
import com.filesaver.domain.core.DTO.Username;
import com.filesaver.domain.core.enums.UserRoles;
import com.filesaver.domain.core.enums.UserStatus;
import com.filesaver.domain.errors.SameContentFieldsError;

public class User {
  private String id = null;
  private final Username username;
  private Password password;
  private UserRoles role = UserRoles.COMMON;
  private UserStatus satus = UserStatus.ACTIVE;

  public User(String id, Username username, Password password, UserRoles role, UserStatus status) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.satus = status;
  }
  public User(Username username, Password password, UserRoles role, UserStatus status) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.satus = status;
  }
  public User(Username username, Password password){
    this.username = username;
    this.password = password;
  }

  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    if(id != null) return;
    this.id = id;
  }

  public Username getUsername() {
    return username;
  }

  public Password getPassword() {
    return password;
  }

  public void setPassword(Password password) throws SameContentFieldsError{
    if(password.equals(this.password)) {throw new SameContentFieldsError("current password", "new password");}
    this.password = password;
  }

  public UserRoles getRole() {
    return role;
  }

  public void setRole(UserRoles role) {
    this.role = role;
  }

  public UserStatus getSatus() {
    return satus;
  }

  public void setSatus(UserStatus satus) {
    this.satus = satus;
  }

  public boolean equals (User user){
    boolean comparation = this.username.equals(user.getUsername()) && this.password.equals(user.getPassword()) && this.role.equals(user.getRole()) && this.satus.equals(user.getSatus()) && this.id.equals(user.getId());

    return comparation;
  }

}
