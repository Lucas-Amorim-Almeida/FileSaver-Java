package com.filesaver.domain.core;

import java.util.List;

import com.filesaver.domain.core.DTO.Name;

public class File extends Node{
  private String filename;
  private String ext;

  public File(Name fullFilename, Directory parent) throws Exception{
    super(fullFilename, parent, null);
    
    String[] fullName = fullFilename.toString().split("[.]");
    filename = fullName[0];
    ext = fullName[1];
    
  }

  public File(String filename, String extension, Directory parent) throws Exception{
    super(new Name(filename + "." + extension), parent, null);

    this.filename = filename;
    ext = extension;
  }

  @Override
  public void rename(Name name) {
    String strName = name.toString();
    if(strName.split("[.]").length < 2){
      return;
    }
    //verifica se há outro Node com o mesmo Name no mesmo level da tree;
    List<Node> childrenOfThisParent = parent.getChildren();
    for (Node child : childrenOfThisParent){
      if(name.equals(child.getName())){
        return;
      }
    }
    super.name = name;
  
  }

  @Deprecated
  public void addChild(Node node) throws Exception {
    throw new UnsupportedOperationException("Unimplemented method 'addChild'");
  }

  @Deprecated
  public void removeChild(Node node) throws Exception {
    throw new UnsupportedOperationException("Unimplemented method 'removeChild'");
  }

  public String getFilename() {
    return filename;
  }

  public String getExt() {
    return ext;
  }

  
}
