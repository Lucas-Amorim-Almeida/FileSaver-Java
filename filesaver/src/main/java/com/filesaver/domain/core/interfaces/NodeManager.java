package com.filesaver.domain.core.interfaces;

import com.filesaver.domain.core.Node;

public interface NodeManager {

  public boolean exists(Node node);

  public boolean create(Node node);

  public boolean delete(Node node);

  public Node move(Node currentNode, Node targetNode) throws Exception;

  public Node copy(Node currentNode, Node targetNode) throws Exception;


}
