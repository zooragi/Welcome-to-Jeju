package com.welcomeToJeJu.moj.handler;

import java.util.List;
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
    //    while (true) {
    System.out.println("[좋아요 등록하기]");

    String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if (title.equals("") || title.length() == 0) {
      System.out.println("좋아요 등록하기 취소!");
      System.out.println();
      return;
    }

    List<Theme> themeList = themeDao.findByTitle(title);

    if (themeList.isEmpty()) {
      System.out.println("등록된 테마 없음!");
      System.out.println();
      return;
    }

    Theme likedTheme = null;

    for (Theme theme : themeList) {
      System.out.printf("<%d>\n", theme.getNo());
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.println();

      int no = Prompt.inputInt("좋아요 할 테마 번호 > ");
      System.out.println();
      if(theme.getNo() == no) {
        likedTheme = theme;
      }
    }

    if (likedTheme.getThemeOwnerName().equals(AuthLoginHandler.getLoginUser().getNickName())) {
      System.out.println("본인의 테마 좋아요 등록 불가!");
      return;
    }

    for(String name : likedTheme.getLikedThemeUsers()) {
      if(name.equals(AuthLoginHandler.getLoginUser().getNickName())) {
        System.out.println("이미 등록한 좋아요!");
        return;
      }
    }


    themeDao.likedThemeInsert(likedTheme, AuthLoginHandler.getLoginUser().getNickName());


    System.out.println("좋아요 등록 완료!");
    //    }
  }


}