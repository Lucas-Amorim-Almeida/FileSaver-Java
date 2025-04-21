package com.filesaver.domain.application.User.Login;

import com.filesaver.domain.core.DTO.Password;
import com.filesaver.domain.core.DTO.Username;

public class LoginInputData {
  private final Username username;
  private final Password password;

  public LoginInputData(String username, String password) throws Exception{
    this.username = new Username(username);
    this.password = new Password(password);
    
    this.password.setHash(false);
  }

  public Username getUsername() {
    return username;
  }

  public Password getPassword() {
    return password;
  }
}
