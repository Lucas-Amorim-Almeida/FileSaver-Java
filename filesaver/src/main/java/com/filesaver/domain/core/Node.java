package com.filesaver.domain.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.filesaver.domain.core.DTO.Name;
import com.filesaver.domain.core.interfaces.Prototype;

public abstract class Node implements Prototype<Node>  {
  private String path;
  protected Name name;

  protected Node parent = null;
  protected List<Node> children = null;

  public Node(Name name, Node parent, List<Node> children) throws Exception {
    this.name = name;
    this.parent = parent;
    this.children = children;

    pathAssembler();
    relationshipBuilder();
  }
  public Node(Name name, List<Node> children) throws Exception {
    this.name = name;
    this.children = children;
    pathAssembler();
    relationshipBuilder();
  }
  public Node(Name name, Node parent) throws Exception {
    this.name = name;
    this.parent = parent;
    pathAssembler();
    relationshipBuilder();
  }
  public Node(Name name) throws Exception {
    this.name = name;
    pathAssembler();
    relationshipBuilder();
  }
  
  public abstract void rename(Name name);
  public abstract void addChild(Node node) throws Exception;
  public abstract void removeChild(Node node) throws Exception;

  public Name getName() {
    return name;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }

  public Node getParent() {
    return parent;
  }
  public void setParent(Node node) throws Exception {
    this.parent = node;
    node.addChild(this);
    pathAssembler();
  }

  public List<Node> getChildren() {
    return children;
  }

  protected void pathAssembler () {
    StringBuilder sbPath = new StringBuilder();
    if(parent != null){
      sbPath.append(parent.getPath());
      sbPath.append("/");
    }
    sbPath.append(name.toString());

    path = sbPath.toString();
  }

  private void relationshipBuilder() throws Exception {
    if(parent != null){
      //não se pode adiconar children a files por esse motivo é necessário que o parent seja uma 
      // instancia da subclasse Directory
      if(parent.children == null && parent instanceof Directory){
        parent.children = new ArrayList<Node>();
      }
      parent.addChild(this);
    }
    if(children != null){
      for(Node child : children){
        child.setParent(this);
      }
    }
  }

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;

    // Comparar os pais
    if (this.parent == null && node.parent != null) return false;
    if (this.parent != null && !this.parent.equals(node.parent)) return false;

    // Comparar nome e caminho
    if (!this.name.equals(node.name)) return false;
    if (!this.path.equals(node.path)) return false;

    // Comparar os filhos
    if (this.children == null && node.children != null) return false;
    if (this.children != null && node.children == null) return false;
    if (this.children != null && node.children != null) {
        if (this.children.size() != node.children.size()) return false;
        for (int i = 0; i < this.children.size(); i++) {
            if (!this.children.get(i).equals(node.children.get(i))) {
                return false;
            }
        }
    }

    return true;
  }

  @Override
  public int hashCode() {
      return Objects.hash(name, path, parent, children);
  }

  @Override
  public Node clone() {
    try {
        // Cria um novo objeto da mesma classe
        Node cloned = (Node) super.clone();

        // Clona o nome (assumindo que Name é imutável, senão clone ele também)
        cloned.name = this.name;

        // Não copia o parent (evita loop), ele será setado pelo relacionamento
        cloned.parent = null;

        // Cria nova lista de filhos
        if (this.children != null) {
            List<Node> clonedChildren = new ArrayList<>();
            for (Node child : this.children) {
                Node clonedChild = child.clone();
                clonedChild.setParent(cloned); // Estabelece relacionamento
                clonedChildren.add(clonedChild);
            }
            cloned.children = clonedChildren;
        } else {
            cloned.children = null;
        }

        // Atualiza caminho
        cloned.pathAssembler();

        return cloned;
    } catch (Exception e) {
        throw new RuntimeException("Clone failed", e);
    }
  }

}
