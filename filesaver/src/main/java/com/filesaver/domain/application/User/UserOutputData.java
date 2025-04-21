package com.filesaver.domain.application.User;


public class UserOutputData {
  private String id;
  private String username;
  private String role;
  private String pathToRoot;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }

  public String getPathToRoot() {
    return pathToRoot;
  }
  public void setPathToRoot(String path) {
    pathToRoot = path;
  }

  
}
