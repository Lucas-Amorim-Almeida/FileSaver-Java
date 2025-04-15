package com.filesaver.utils;

public class ConfigUtil {
  private String application_root;
  private String root_marker;
  private String bin;
  
  public void setApplication_root(String application_root) {
    this.application_root = application_root;
  }
  public void setRoot_marker(String root_marker) {
    this.root_marker = root_marker;
  }
  public void setBin(String bin) {
    this.bin = bin;
  }
  
  public String getApplication_root() {
    return application_root;
  }
  public String getRoot_marker() {
    return root_marker;
  }
  public String getBin() {
    return bin;
  }

  
}
