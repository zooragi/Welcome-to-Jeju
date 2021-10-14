package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class PlaceDeleteHandler implements Command {

  ThemeDao themeDao;

  public PlaceDeleteHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    //    while (true) {
    System.out.println("[장소 삭제하기]");

    String title = (String) request.getAttribute("themeTitle");

    User loginUser = AuthLoginHandler.getLoginUser();
    Collection<Theme> themeList = themeDao.findLoginUserAll(loginUser);

    Theme theme = null;
    for(Theme theme1 : themeList) {
      if(theme1.getTitle().equals(title)) {
        theme = theme1;
      }
    }

    if(theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    String storeName = Prompt.inputString("장소 이름(취소 : 엔터) > ");

    if (storeName.equals("") || storeName.length() == 0) {
      System.out.println("장소 삭제하기 취소!");
      System.out.println();
      return;
    }

    Place place = null;
    for (Place p : theme.getPlaceList()) {
      if (p.getStoreName().equals(storeName)) {
        place = p;
        break;
      }
    }

    if (place == null) {
      System.out.println("등록된 장소 없음!");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장소 삭제하기 취소!");
      System.out.println();
      return;
    }

    themeDao.placeDelete(theme, place);
  }
  //  }
}