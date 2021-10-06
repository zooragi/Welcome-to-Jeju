package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.util.Prompt;

public class PlaceDeleteHandler extends AbstractPlaceHandler{

  @Override
  public void execute(CommandRequest request) {
    while (true) {
      System.out.println("[장소 삭제하기]");

      Theme theme = (Theme) request.getAttribute("searchedTheme");

      if (theme == null) {
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
      for (Place list : theme.getPlaceList()) {
        if (list.getStoreName().equals(storeName)) {
          place = list;
          break;
        }
      }

      if (place == null) {
        System.out.println("등록된 장소 없음!");
        System.out.println();
        continue;
      }

      String input = Prompt.inputString("삭제하기(y/N) > ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("장소 삭제하기 취소!");
        System.out.println();
        return;
      }

      theme.getPlaceList().remove(place);
      System.out.println("장소 삭제하기 완료!");
    }
  }
}