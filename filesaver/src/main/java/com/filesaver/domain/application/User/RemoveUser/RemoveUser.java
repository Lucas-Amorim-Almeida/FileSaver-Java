package com.filesaver.domain.application.User.RemoveUser;

import com.filesaver.domain.core.User;
import com.filesaver.domain.core.DTO.Query;
import com.filesaver.domain.core.enums.UserStatus;
import com.filesaver.domain.core.interfaces.Repository;
import com.filesaver.domain.errors.EntityNotFoundError;
import com.filesaver.domain.errors.InternalApplicationError;
import com.filesaver.domain.errors.RequiredFieldError;

public class RemoveUser {
  private final Repository<User, Query<String>> repository;
  private RemoveUserOutputData outputData;
  
  public RemoveUser(Repository<User, Query<String>> repository, RemoveUserOutputData outputData) {
    this.repository = repository;
    this.outputData = outputData;
  }

  public void execute(RemoveUserInputData inputData) throws Exception{
    fieldsInitialValidation(inputData);
    var userId = inputData.getUserId();

    var query = new Query<String>("id", userId);
    User user = repository.getOne(query);
    if(user == null){
      throw new EntityNotFoundError("User");
    }

    //Remoção é feita atravez de um update passando o user com uma flag DELETED do UserStatus;
    var deleteFlag = UserStatus.DELETED;
    user.setSatus(deleteFlag);

    var dbUser = repository.update(user);
    if(dbUser == null || dbUser.getSatus() != deleteFlag){
      outputData.setMessage("Unable to delete user.");
      throw new InternalApplicationError();
    }

    outputData.setMessage("User deleted successfully.");

  }

  private void fieldsInitialValidation(RemoveUserInputData inputData) throws Exception {
    var userId = inputData.getUserId();

    if(userId.equals("") || userId == null){
      throw new RequiredFieldError("User ID");
    }
  }
}
