package com.welcomeToJeJu.moj.handler;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;

public class AllThemeListHandler {
  List<Theme> themeList;

  public AllThemeListHandler(List<Theme> themeList) {
    this.themeList = themeList;
  }
  public void execute(CommandRequest request) {
    System.out.println("[전체 테마 목록보기]");
    int i = 1;
    for(Theme theme : themeList) {
      if(!theme.isPublic()) continue;
      System.out.printf("<%d>\n",i++);
      System.out.println("테마 이름 > " + theme.getTitle());
      System.out.println("해시 태그 > " + theme.getHashtags().toString());
    }
  }
}
