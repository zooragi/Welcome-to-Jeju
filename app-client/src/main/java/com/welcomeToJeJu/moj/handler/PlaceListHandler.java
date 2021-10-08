package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;

public class PlaceListHandler implements Command {

  ThemeDao themeDao;

  public PlaceListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    //    while (true) {
    System.out.println("[장소 목록보기]");

    String themeName = (String) request.getAttribute("themeTitle");

    Theme theme = themeDao.selectOneByTitle(themeName);

    if(theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    int index = 1;

    System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());

    for (Place place : theme.getPlaceList()) {
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
      System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
      System.out.printf("위도 > %s\n", place.getxCoord());
      System.out.printf("경도 > %s\n", place.getyCoord());
      System.out.printf("장소 후기> %s\n", place.getComments().toString());
      System.out.println();
    }
    //    }
  }


}