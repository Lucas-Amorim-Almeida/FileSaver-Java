package com.filesaver.domain.core.DTO;

import java.util.regex.Pattern;
import com.filesaver.domain.core.interfaces.Encrypter;
import com.filesaver.domain.errors.InvalidFieldError;

public class Password {
  String password;
  boolean isHash = false;

  public Password(String password) throws InvalidFieldError{
    this.password = password;
    if(!this.validadte()){
      throw new InvalidFieldError("Password");
    }
  }

  public boolean validadte() {
    if(isHash){
      return true;
    }

    String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$";
    Pattern passwordPattern = Pattern.compile(passwordRegex);
    return passwordPattern.matcher(password).matches();
  }

  @Override
  public String toString() {return password;}//

  public void hashPassword(Encrypter encrypter){ 
    this.password = encrypter.encrypt(password);
    this.isHash = true;
  }

  public boolean equals(Password password, Encrypter encrypter){
    boolean comparation = false;
    if(this.isHash){
      comparation = password.getIsHash()? 
        this.password.equals(password.toString()) : 
        encrypter.compare(password.toString(), this.password);
      } else {
        comparation = !password.getIsHash()? 
        this.password.equals(password.toString()) : 
        encrypter.compare(this.password, password.toString());
    }
    return comparation;
  }

  public boolean getIsHash() {return isHash;}

  public void setHash(boolean isHash) {
    this.isHash = isHash;
  }

}
