package com.filesaver.domain.core.DTO;

import java.util.regex.Pattern;

import com.filesaver.domain.errors.InvalidFieldError;

public class Name {
  private String name;

  public Name (String name) throws InvalidFieldError{
    if(!validate(name)){
      throw new InvalidFieldError("Node name");
    }

    this.name = name;
  }

  private boolean validate(String name) {
    String nameRegex = "^[a-zA-Z0-9_\\-@# .]+$";
    Pattern namePattern = Pattern.compile(nameRegex);
    return namePattern.matcher(name).matches();
  }

  @Override
  public String toString() {
    return name;
  }

  public void setName(String name) {
    if(!validate(name)){
      throw new InvalidFieldError("Node name");
    }
    this.name = name;
  }

  public boolean equals(Name othername){
    return name.equals(othername.toString());
  }
}
