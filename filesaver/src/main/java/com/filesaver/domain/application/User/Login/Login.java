package com.filesaver.domain.application.User.Login;

import com.filesaver.domain.application.User.UserOutputData;
import com.filesaver.domain.core.ApplicationRoot;
import com.filesaver.domain.core.Bin;
import com.filesaver.domain.core.Root;
import com.filesaver.domain.core.User;
import com.filesaver.domain.core.DTO.Query;
import com.filesaver.domain.core.interfaces.Encrypter;
import com.filesaver.domain.core.interfaces.NodeManager;
import com.filesaver.domain.core.interfaces.Repository;
import com.filesaver.domain.errors.DataMismatchError;
import com.filesaver.domain.errors.EntityNotFoundError;
import com.filesaver.domain.errors.InternalApplicationError;

public class Login {
  private final Repository<User, Query<String>> repository;
  private final Encrypter encrypter;
  private UserOutputData outputData;
  private final NodeManager nodeManager;

  public Login(Repository<User, Query<String>> repository, Encrypter encrypter, UserOutputData outputData, NodeManager nodeManager) {
    this.repository = repository;
    this.encrypter = encrypter;
    this.outputData = outputData;
    this.nodeManager = nodeManager;
  }

  public void execute(LoginInputData inputData, ApplicationRoot applicationRoot) throws Exception{
    var username = inputData.getUsername();
    var password = inputData.getPassword();

    var query = new Query<String>("username", username.toString());
    User user = repository.getOne(query);
    if(user == null) {
      throw new EntityNotFoundError("User");
    }

    var isValidPassword = password.equals(user.getPassword(), encrypter);
    if(!isValidPassword) {
      throw new DataMismatchError("Password");
    }

    var userRootDir = userRootBuilder(user, applicationRoot);
    
    outputData.setId(user.getId());
    outputData.setUsername(user.getUsername().toString());
    outputData.setRole(user.getRole().getRole());
    outputData.setPathToRoot(userRootDir.getPath());

  }

  private Root userRootBuilder(User user, ApplicationRoot applicationRoot) throws Exception{
    Root userRoot = new Root(user);
    new Bin(userRoot);

    if(!nodeManager.exists(userRoot)){
      throw new InternalApplicationError();
    }
    return userRoot;
  }
}
