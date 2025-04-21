package com.filesaver.domain.application.User.CreateUser;

import com.filesaver.domain.application.User.UserOutputData;
import com.filesaver.domain.core.ApplicationRoot;
import com.filesaver.domain.core.Bin;
import com.filesaver.domain.core.Root;
import com.filesaver.domain.core.User;
import com.filesaver.domain.core.DTO.Query;
import com.filesaver.domain.core.interfaces.Encrypter;
import com.filesaver.domain.core.interfaces.NodeManager;
import com.filesaver.domain.core.interfaces.Repository;
import com.filesaver.domain.errors.InternalApplicationError;

public class CreateUser {
  
  private Repository<User, Query<String>> repository;
  private UserOutputData outputData;
  private Encrypter encrypter;
  private final NodeManager nodeManager;

  public CreateUser(Repository<User, Query<String>> repository, UserOutputData outputData, Encrypter encrypter, NodeManager nodeManager){
    this.repository = repository;
    this.encrypter = encrypter;
    this.outputData = outputData;
    this.nodeManager = nodeManager;
  }

  public void execute (CreateUserInputData input, ApplicationRoot appRoot) throws Exception {
    var username = input.getUsername();
    var password = input.getPassword();
    var role = input.getRole();
    var status = input.getStatus();

    User user = new User(username, password, role, status);
    user.getPassword().hashPassword(encrypter);
    var dbUser = repository.save(user);

    if(dbUser == null) {
      throw new InternalApplicationError();
    }

    var userRootDir = userRootBuilder(dbUser, appRoot);

    outputData.setId(dbUser.getId());
    outputData.setUsername(dbUser.getUsername().toString());
    outputData.setRole(dbUser.getRole().getRole());
    outputData.setPathToRoot(userRootDir.getPath());
  }

  private Root userRootBuilder(User user, ApplicationRoot applicationRoot) throws Exception{
    Root userRoot = new Root(user);
    new Bin(userRoot);

    nodeManager.create(userRoot);
    return userRoot;
  }

}
