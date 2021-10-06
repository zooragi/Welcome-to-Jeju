package com.welcomeToJeJu.moj.handler;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;

public abstract class AbstractMyMapHandler implements Command {

  List<User> userList;

  public AbstractMyMapHandler(List<User> userList) {
    this.userList = userList;
  }

  protected Theme findByTitle(String title) {
    for(User user : userList) {
      for (Theme theme : user.getThemeList()) {
        if (theme.getTitle().equals(title))
          return theme;
      }
    }
    return null;
  }


}
