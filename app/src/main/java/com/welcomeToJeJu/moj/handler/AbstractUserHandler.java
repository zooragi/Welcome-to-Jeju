package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.User;

public abstract class AbstractUserHandler implements Command {
  List<User> userList;
  public AbstractUserHandler(List<User> userList) {
    this.userList = userList;
  }
  protected User findByNo(int no) {
    for (User user : userList) {
      if (user.getNo() == no) {
        return user;
      }
    }
    return null;
  }
}
