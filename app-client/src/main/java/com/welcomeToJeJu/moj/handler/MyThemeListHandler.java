package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;

public class MyThemeListHandler implements Command {

  ThemeDao themeDao;

  public MyThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 목록보기]");

    User loginUser = AuthLoginHandler.getLoginUser();
    Collection<Theme> themeList = themeDao.findLoginUserAll(loginUser);

    if (themeList.size() == 0) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    for (Theme theme : themeList) {
      System.out.printf("<%d>\n", theme.getNo());
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.printf("%s테마\n", theme.isPublic() ? "공개" : "비공개");
      if (theme.isPublic()) {
        System.out.printf("조회수 > %d\n", theme.getViewCount());
      }
      System.out.println();
    }
  }
}