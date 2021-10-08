package com.welcomeToJeJu.moj.handler;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;

public abstract class AbstractMyMapHandler implements Command {

  //  List<User> userList;
  List<Theme> themeList;
  public AbstractMyMapHandler(List<Theme> themeList) {
    this.themeList = themeList;
  }

  protected Theme findByTitle(String title) {
    for (Theme theme : themeList) {
      if (theme.getTitle().equals(title))
        return theme;
    }
    return null;
  }


}
