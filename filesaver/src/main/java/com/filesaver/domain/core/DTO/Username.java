package com.filesaver.domain.core.DTO;

import java.util.regex.Pattern;
import com.filesaver.domain.errors.InvalidFieldError;

public class Username {
  private final String username;

  public Username(String username) throws InvalidFieldError{
    this.username = username.toLowerCase();
    if(!this.validate()){
      throw new InvalidFieldError("Username");
    }
  }

  public boolean validate() {
    // Regex: não começa com número, seguido de letras e números
    String usernameRegex = "^(?!\\d)[a-zA-Z0-9_-]+$";
    Pattern usernamePattern = Pattern.compile(usernameRegex);
    return usernamePattern.matcher(username).matches();
  }


  @Override
  public String toString() {
    return username;
  }


  public boolean equals(Username username) {
    boolean comparation = this.username.equals(username.toString());

    return comparation;
  }

}
