package com.filesaver.domain.core;

import com.filesaver.domain.core.DTO.Name;
import com.filesaver.domain.errors.EntityNotFoundError;
import com.filesaver.sevrvices.JacksonAdapter;
import com.filesaver.utils.ConfigUtil;

public class Root extends Directory {
  
  public Root(User user) throws Exception{
    super(rootNameAssembler(user), ApplicationRoot.getInstance());
  }

  private static Name rootNameAssembler(User user) throws Exception {
    JacksonAdapter jsonHandler = JacksonAdapter.getInstance();
    ConfigUtil configs = jsonHandler.getConfigs();

    final String ROOT_MARKER = configs.getRoot_marker();
    final String USERNAME = user.getUsername().toString();
    return new Name(ROOT_MARKER + USERNAME);
  }

  @Deprecated
  public void setParent(Node node) throws Exception {}
  @Deprecated
  public void rename(Name name) {}

  @Override
  public void removeChild(Node node) throws EntityNotFoundError {
    if(node instanceof Bin){
      return;
    }
    super.removeChild(node);
  }

  
}
