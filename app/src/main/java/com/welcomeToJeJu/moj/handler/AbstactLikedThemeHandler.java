package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

public abstract class AbstactLikedThemeHandler implements Command {

  List<User> userList;

  public AbstactLikedThemeHandler(List<User> userList) {
    this.userList = userList;
  }

  protected Theme findByTitle(String title) {
    for (User user : userList) {
      for (Theme theme : user.getThemeList()) {
        if (theme.getTitle().equals(title)) {
          return theme;
        }
      }
    }
    return null;
  }


}