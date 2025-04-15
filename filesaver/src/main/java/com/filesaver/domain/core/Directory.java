package com.filesaver.domain.core;

import java.util.ArrayList;
import java.util.List;

import com.filesaver.domain.core.DTO.Name;
import com.filesaver.domain.errors.EntityAlreadyExistsError;
import com.filesaver.domain.errors.EntityNotFoundError;

public class Directory extends Node {

  public Directory(Name name, Directory parent, List<Node> children)throws Exception {
    super(name, parent, children);
  }
  public Directory(Name name, List<Node> children)throws Exception {
    super(name, children);
  }
  public Directory(Name name, Directory parent)throws Exception {
    super(name, parent, new ArrayList<Node>());
  }
  public Directory(Name name) throws Exception {
    super(name, new ArrayList<Node>());
  }

  @Override
  public void rename(Name name){
    super.name = name;
  }

  @Override
  public void addChild(Node node) throws Exception{
    if(children == null){
      children = new ArrayList<Node>();
    }
    for(Node child : super.children){
      if(child.equals(node)){
        throw new EntityAlreadyExistsError("Node");
      }
    }
    
    super.children.add(node);
    node.parent = this;
    node.pathAssembler();
  }

  @Override
  public void removeChild(Node node) throws EntityNotFoundError{
    
    for(int i = 0; i < super.children.size(); i++ ){
      Node child = super.children.get(i);

      if(child.equals(node)){
        super.children.remove(i);
        return;
      }
    }
    throw new EntityNotFoundError("Node");
  }
  
  
}
