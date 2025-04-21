package com.filesaver.domain.application.User.ChangePassword;

import com.filesaver.domain.core.DTO.Password;

public class ChangePasswordInputData {
  private final String userId;
  private final Password currentPassword;
  private final Password newPassword;

  public ChangePasswordInputData(String currentPassword, String newPassword, String userId){
    this.currentPassword = new Password(currentPassword);
    this.newPassword = new Password(newPassword);
    this.userId = userId;

    this.currentPassword.setHash(false);
    this.newPassword.setHash(false);
  }

  public String getUserId() {
    return userId;
  }

  public Password getCurrentPassword() {
    return currentPassword;
  }

  public Password getNewPassword() {
    return newPassword;
  }
}
