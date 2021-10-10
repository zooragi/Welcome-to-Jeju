package com.welcomeToJeJu.moj.handler;

import java.util.List;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;

public class LikedThemeListHandler implements Command{

  ThemeDao themeDao;

  public LikedThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아요 목록보기]");


    List<Theme> themeList = themeDao.findAll();

    for(Theme theme : themeList) {
      for(String name : theme.getLikedThemeUsers()) {
        if(name.equals(AuthLoginHandler.getLoginUser().getNickName())) {
          System.out.printf("<%d>\n", theme.getNo());
          System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
          System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
          System.out.println();
        } else {
          System.out.println("등록된 좋아요 없음!");
          System.out.println();
          return;
        }
      }
    }



    //    if (AuthLoginHandler.getLoginUser().getLikedThemes().size() == 0) {
    //      System.out.println("등록된 좋아요 없음!");
    //      System.out.println();
    //      return;
    //    }
    //
    //    for (Theme theme : AuthLoginHandler.getLoginUser().getLikedThemes()) {
    //      System.out.printf("<%d>\n", theme.getNo());
    //      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
    //      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
    //      System.out.println();
    //    }
    //  }
  }

}