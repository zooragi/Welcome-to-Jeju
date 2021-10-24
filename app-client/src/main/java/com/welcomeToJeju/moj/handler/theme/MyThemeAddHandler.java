package com.welcomeToJeju.moj.handler.theme;

import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class MyThemeAddHandler implements Command {

  ThemeDao themeDao;

  public MyThemeAddHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[나의 테마 등록하기]");

    Theme theme = new Theme();

    List<Theme> themeList = themeDao.findAll(); //

    theme.setTitle(Prompt.inputString("테마 이름(취소 : 엔터) > "));

    if (theme.getTitle().equals("")) {
      System.out.println("나의 테마 등록하기 취소!");
      return;
    }

    // 카테고리
    int categoryNo;
    List<String> categories = new ArrayList<>();
    categories.add("식당");
    categories.add("카페");
    categories.add("관광명소");
    categories.add("기타");
    while (true) {
      int index = 1;
      for (String category : categories) {
        System.out.printf("%d. %s ", index++, category);
      }
      System.out.println();
      categoryNo = Prompt.inputInt("카테고리 번호 > ");
      if (categoryNo > categories.size() || categoryNo < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      System.out.println();
      break;
    }
    theme.setCategory(categories.get(categoryNo - 1));

    // 해시태그
    while (true) {
      String inputHashtag  = Prompt.inputString("해시태그(완료 : 엔터) > ");

      if (inputHashtag.equals("")) break;

      theme.getHashtags().add(inputHashtag);
    }

    // 공개
    String publicOption = Prompt.inputString("공개(Y/n) > ");

    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      theme.setPublic(true);
    }

    theme.setThemeOwner(AuthLoginHandler.getLoginUser());

    themeDao.insert(theme);

  }

}
