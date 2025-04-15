package com.filesaver.domain.core;


import java.util.ArrayList;
import java.util.List;

import com.filesaver.domain.core.DTO.Name;
import com.filesaver.domain.errors.EntityNotFoundError;
import com.filesaver.sevrvices.JacksonAdapter;
import com.filesaver.utils.ConfigUtil;

public class Bin extends Directory{
  public Bin(Root userRoot) throws Exception{
    super(handleBinName(),  userRoot);
  }

  private static Name handleBinName() throws Exception {
    JacksonAdapter jsonHandler = JacksonAdapter.getInstance();
    ConfigUtil configs = jsonHandler.getConfigs();
    Name binName = new Name(configs.getBin());
    
    return binName;
  }

  @Deprecated
  public void setParent(Node node) throws Exception {}

  @Deprecated
  public void rename(Name name) {}

  @Override
  public void addChild(Node node) throws Exception {
    if(node instanceof Root || node instanceof Bin){
      throw new IllegalAccessException("Não é possível deletar a raiz ou a lixeira.");
    }
    this.children.add(node);
    
    Node nodeParent = node.getParent();
    nodeParent.removeChild(node);
  }


  public void restore(Node node) throws EntityNotFoundError{
    Node nodeParent = node.getParent();
    nodeParent.getChildren().add(node);
    removeChild(node);
  }

  public void clear() throws EntityNotFoundError{
    if(children.isEmpty()){
      return;
    }
    
    List<Node> toRemove = new ArrayList<Node>(children);
    for(Node child : toRemove){
      children.remove(child);
    }
  }
}
