package com.welcomeToJeju.moj.handler.rank;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;

public class ThemeRankHandler implements Command {

  ThemeDao themeDao;

  public ThemeRankHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 순위 보기]");

    int no = 1;
    for(Theme theme : themeDao.sortThemeByViewCount()) {
      System.out.printf("%d위 > %s (조회수 : %d)\n",
          no++, theme.getTitle(), theme.getViewCount());
    }
  }


}
