package com.filesaver.domain.application.User.RemoveUser;

public class RemoveUserInputData {
  private final String userId;

  public RemoveUserInputData(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
