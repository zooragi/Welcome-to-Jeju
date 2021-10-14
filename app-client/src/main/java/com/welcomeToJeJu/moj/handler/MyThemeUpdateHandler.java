package com.welcomeToJeJu.moj.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class MyThemeUpdateHandler implements Command {


  ThemeDao themeDao;
  public MyThemeUpdateHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 수정하기]");
    int categoryNum;

    String title = (String) request.getAttribute("themeTitle");

    User loginUser = AuthLoginHandler.getLoginUser();
    Collection<Theme> themeList = themeDao.findLoginUserAll(loginUser);

    Theme updateTheme = null;
    for(Theme theme : themeList) {
      if(theme.getTitle().equals(title)) {
        updateTheme = theme;
      }
    }

    if(updateTheme ==null) {
      System.out.println("등록된 테마 없음!");
    }

    String newTitle = Prompt.inputString("테마 제목 > ");
    if(newTitle.length() == 0) {
      System.out.println("수정할 테마 제목 입력!");
      return;
    }
    List<String> categories = new ArrayList<>();
    categories.add("식당");
    categories.add("카페");
    categories.add("관광명소");
    categories.add("기타");
    while (true) {
      int index = 1;
      for(String category : categories) {
        System.out.printf("%d. %s ",index++,category);
      }
      System.out.println();
      categoryNum = Prompt.inputInt("카테고리 번호 > ");
      if(categoryNum > categories.size() || categoryNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      System.out.println();
      break;
    }

    List<String> hashtagList = new ArrayList<>();

    while (true) {
      String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
      if (input.length() == 0)
        break;
      hashtagList.add(input);
    }

    boolean isPublic = false;

    String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      isPublic = true;
    }

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 수정 취소!");
      return;
    }

    Theme temp = new Theme();


    temp.setTitle(newTitle);
    temp.setHashtags(hashtagList);
    temp.setCategory(categories.get(categoryNum-1));
    temp.setPublic(isPublic);

    themeDao.update(temp);
  }

}
