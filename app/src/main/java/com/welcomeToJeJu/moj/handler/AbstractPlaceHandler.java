package com.welcomeToJeJu.moj.handler;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;

public abstract class AbstractPlaceHandler implements Command{

  List<Theme> themeList;
  public AbstractPlaceHandler(List<Theme> themeList) {
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
