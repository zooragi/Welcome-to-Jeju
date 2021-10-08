package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.util.Prompt;

public class MyThemeDeleteHandler implements Command {

  ThemeDao themeDao;
  UserDao userDao;
  public MyThemeDeleteHandler(ThemeDao themeDao, UserDao userDao) {
    this.themeDao = themeDao;
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 삭제하기]");
    String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if(title.equals("")) {
      System.out.println("나의 테마 삭제 취소!");
      return;
    }

    Theme theme = themeDao.selectOneByTitle(title);

    if(theme ==null) {
      System.out.println("등록된 테마 없음!");
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 삭제 취소!");
      return;
    }

    themeDao.delete(theme.getTitle());
    userDao.themeDelete(theme);
  }

}
