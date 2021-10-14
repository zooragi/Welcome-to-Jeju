package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;

public class AllThemeListHandler implements Command {
  ThemeDao themeDao;

  public AllThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }


  public void execute(CommandRequest request) throws Exception {
    System.out.println("[전체 테마 목록보기]");

    Collection<Theme> themeList = themeDao.findAll();

    int i = 1;
    for(Theme theme : themeList) {
      if(!theme.isPublic()) continue;
      System.out.printf("<%d>\n",i++);
      System.out.println("테마 이름 > " + theme.getTitle());
      System.out.println("해시 태그 > " + theme.getHashtags().toString());
    }
  }
}
