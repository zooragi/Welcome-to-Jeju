package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.util.Prompt;

public class LikedThemeAddHandler implements Command {

  ThemeDao themeDao;

  public LikedThemeAddHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      System.out.println("[좋아요 등록하기]");

      String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

      if (title.equals("") || title.length() == 0) {
        System.out.println("좋아요 등록하기 취소!");
        System.out.println();
        return;
      }

      Theme theme = themeDao.findByTitle(title);

      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        System.out.println();
        continue;
      }

      for(String name : theme.getLikedThemeUsers()) {
        if(name.equals(AuthLoginHandler.getLoginUser().getNickName())) {
          System.out.println("이미 등록한 좋아요!");
          return;
        }
      }


      if (theme.getThemeOwnerName().equals(AuthLoginHandler.getLoginUser().getNickName())) {
        System.out.println("본인의 테마 좋아요 등록 불가!");
        return;
      }

      themeDao.likedThemeInsert(theme, AuthLoginHandler.getLoginUser().getNickName());


      System.out.println("좋아요 등록 완료!");
    }
  }


}