package com.filesaver.domain.errors;

public class SameContentFieldsError extends Exception {
  
  public SameContentFieldsError(String stFieldName, String ndFieldName) {
    super(msgBuilder(stFieldName, ndFieldName));
  }

  private static String msgBuilder(String stFieldname, String ndFieldname) {
    StringBuilder sbMsg = new StringBuilder();
    sbMsg.append(stFieldname);
    sbMsg.append(" and ");
    sbMsg.append(ndFieldname);
    sbMsg.append(" must not be equal.");
    return sbMsg.toString();
  }

}
