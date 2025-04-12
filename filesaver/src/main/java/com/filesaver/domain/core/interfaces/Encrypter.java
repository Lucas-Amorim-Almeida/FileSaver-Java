package com.filesaver.domain.core.interfaces;

public interface Encrypter {
  public String encrypt(String plainText);
  public boolean compare(String plainText, String encryptedText);
}
