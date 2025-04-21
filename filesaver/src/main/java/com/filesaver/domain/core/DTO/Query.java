package com.filesaver.domain.core.DTO;

public class Query<Value> {
  private String keyQuery;
  private Value value;

  public Query(String key, Value value){
    this.keyQuery = key;
    this.value = value;
  }

  public String getKeyQuery() {
    return keyQuery;
  }

  public Value getValue() {
    return value;
  }
  
  public String getAsJsonString(){
    StringBuffer sbJson = new StringBuffer();
    sbJson.append("{ ");
    sbJson.append(keyQuery);
    sbJson.append(": ");
    sbJson.append(value.toString());
    sbJson.append(" }");

    return sbJson.toString();
  }
  
}
