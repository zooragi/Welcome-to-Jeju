package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.domain.Theme;

public abstract class AbstractPlaceHandler implements Command{
  public Theme findByTitle(String themeTitle) {
    for (Theme list : AuthLoginHandler.getLoginUser().getThemeList()) {
      if (list.getTitle().equals(themeTitle)) {
        return list;
      }
    }
    return null;
  }
}
