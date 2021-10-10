package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.util.Prompt;

public class LikedThemeDeleteHandler implements Command {

  ThemeDao themeDao;

  public LikedThemeDeleteHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      System.out.println("[좋아요 삭제하기]");

      String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

      if(title.equals("") || title.length() == 0) {
        System.out.println("좋아요 삭제하기 취소!");
        System.out.println();
        return;
      }

      Theme theme = themeDao.findByTitle(title);

      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        System.out.println();
        continue;
      }

      String input = Prompt.inputString("삭제하기(y/N) > ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("좋아요 삭제하기 취소!");
        System.out.println();
        return;
      }

      themeDao.likedThemeDelete(theme, AuthLoginHandler.getLoginUser().getNickName());
      System.out.println("좋아요 삭제하기 완료!");
    }
  }


}