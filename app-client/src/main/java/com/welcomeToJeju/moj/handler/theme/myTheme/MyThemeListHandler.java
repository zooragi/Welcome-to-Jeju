package com.welcomeToJeju.moj.handler.theme.myTheme;

import java.util.Collection;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class MyThemeListHandler implements Command {

  ThemeDao themeDao;

  public MyThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[나의 테마 목록 보기]");

    Collection<Theme> themeList = themeDao.findAll();

    if (themeList.size() == 0) {
      System.out.println("🌊 테마 없음!");
      return;
    }

    int no = 1;
    for (Theme theme : themeList) {
      if (AuthLoginHandler.getLoginUser().getNo() == theme.getOwner().getNo()) {
        System.out.printf("<%d>\n", no++);
        System.out.printf("제목 > %s\n", theme.getTitle());
        System.out.printf("카테고리 > %s\n", theme.getCategory().getName());
        System.out.printf("해시태그 > %s\n", theme.getHashTags().toString());

        if (theme.isPublic()) {
          System.out.println("공개");
          System.out.printf("조회수 > %s\n", theme.getViewCount());
        } else {
          System.out.println("비공개");
        }
      }
      System.out.println();
    }
  }


}
