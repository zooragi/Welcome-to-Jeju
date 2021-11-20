package com.welcomeToJeju.moj.handler.theme.myTheme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class MyThemeDetailHandler implements Command {

  ThemeDao themeDao;
  Map<String, Map<String,String>> controlMenu = new HashMap<>();
  ArrayList<Theme> myThemeList = new ArrayList<>();

  public MyThemeDetailHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
    themeMenu();    //
    placeMenu();    //
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 상세 보기]");

    // 삭제?
    Collection<Theme> themeList = themeDao.findByUserNo(AuthLoginHandler.getLoginUser().getNo());

    int no = 1;
    for (Theme theme : themeList) {
      System.out.printf("<%d> [%s] %s\n",
          no++, theme.getCategory().getName(), theme.getTitle());
    }

    String input = Prompt.inputString("번호 > ");

    request.setAttribute("theme", input);

    //

    while (true) {
      ArrayList<String> controlMenuListOfKeys = new ArrayList<>(controlMenu.keySet());
      showMenuList(controlMenuListOfKeys);
      int selectedNum = chooseMenu(controlMenuListOfKeys.size());
      if(selectedNum == 0) return;

      Map<String, String> detailMenu = controlMenu.get(controlMenuListOfKeys.get(selectedNum - 1));

      if(detailMenu == null) return;

      ArrayList<String> detailMenuListOfKeys = new ArrayList<>(detailMenu.keySet());
      showMenuList(detailMenuListOfKeys);
      selectedNum = chooseMenu(detailMenuListOfKeys.size());
      if(selectedNum == 0) return;

      request.getRequestDispatcher(detailMenu.get(detailMenuListOfKeys.get(selectedNum-1))).forward(request);
    }

  }

  private void showMenuList(List<String> list) {
    int i = 1;
    for (String key : list) {
      System.out.printf("%d. %s\n", i++, key);
    }
    System.out.println("0. 이전메뉴");
    System.out.println();
  }
  private int chooseMenu(int size) {
    int selectedNum;
    while(true) {
      try {
        selectedNum = Prompt.inputInt("선택> ");
        if(selectedNum > size || selectedNum < 0) {
          System.out.println("잘못된 번호!");
          continue;
        }
        return selectedNum;
      } catch(Exception e) {
        System.out.println("------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        System.out.println("------------------------------------------");
      }
    }
  }

  private void themeMenu() {
    Map<String, String> themeMenu = new HashMap<>();
    themeMenu.put("테마 수정하기", "/myTheme/update");
    themeMenu.put("테마 삭제하기", "/myTheme/delete");
    controlMenu.put("테마관리", themeMenu);
  }

  private void placeMenu() {
    Map<String, String> placeMenu = new HashMap<>();
    placeMenu.put("장소 등록하기", "/place/add");
    placeMenu.put("장소 목록보기", "/place/list");
    placeMenu.put("장소 삭제하기", "/place/delete");
    controlMenu.put("지도관리", placeMenu);
  }


}
