package com.filesaver.domain.application.User.ChangePassword;

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
import com.filesaver.domain.errors.RequiredFieldError;
import com.filesaver.domain.errors.SameContentFieldsError;

public class ChangePassword {
  private final Repository<User, Query<String>> repository;
  private final Encrypter encrypter;
  private UserOutputData outputData;
  private final NodeManager nodeManager;
  
  public ChangePassword(Repository<User, Query<String>> repository, Encrypter encrypter, UserOutputData outputData, NodeManager nodeManager) {
    this.repository = repository;
    this.encrypter = encrypter;
    this.outputData = outputData;
    this.nodeManager = nodeManager;
  }

  public void execute(ChangePasswordInputData inputData, ApplicationRoot applicationRoot) throws Exception{
    fieldsInitialValidation(inputData);

    var userId = inputData.getUserId();
    var currentPassword = inputData.getCurrentPassword();
    var newPassword = inputData.getNewPassword();


    var searchQuery = new Query<String>("id", userId);
    User user = repository.getOne(searchQuery);
    if(user == null){
      throw new EntityNotFoundError("User");
    }

    var isSamePassword = currentPassword.equals(user.getPassword());
    if(!isSamePassword){
      throw new DataMismatchError("Current Password");
    }

    newPassword.hashPassword(encrypter);
    user.setPassword(newPassword);
    
    User dbUser = repository.update(user);
    if(dbUser == null){
      throw new InternalApplicationError();
    }

    var userRootDir = userRootBuilder(user, applicationRoot);
    outputData.setPathToRoot(userRootDir.getPath());

    outputData.setId(dbUser.getId());
    outputData.setUsername(dbUser.getUsername().toString());
    outputData.setRole(user.getRole().getRole());
    
  }
  
  private void fieldsInitialValidation(ChangePasswordInputData inputData) throws Exception {
    var userId = inputData.getUserId();
    var currentPassword = inputData.getCurrentPassword();
    var newPassword = inputData.getNewPassword();

    if(userId.equals("") || userId == null){
      throw new RequiredFieldError("User ID");
    }
    if(!newPassword.equals(currentPassword, encrypter)){
      throw new SameContentFieldsError("Current Password", "New Password");
    }
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
