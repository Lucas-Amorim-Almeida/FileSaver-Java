package com.filesaver.domain.errors;

public class DataMismatchError extends Exception {
  DataMismatchError(String fieldName) {
    super(msgBuilder(fieldName));
  }

  private static String msgBuilder(String fieldname) {
    StringBuilder sbMsg = new StringBuilder();
    sbMsg.append("Data provided in the ");
    sbMsg.append(fieldname);
    sbMsg.append(" field does not match the saved data.");
    return sbMsg.toString();
  }
}
