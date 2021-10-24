package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class SearchHashtagHandler extends AbstractSearchHandler {

  List<Theme> themeList;

  public SearchHashtagHandler(List<User> userList, List<Theme> themeList) {
    super(userList);
    this.themeList = themeList;
  }

  public void execute(CommandRequest request) {

    System.out.println("[해시 태그 검색하기]");

    while (true) {
      String input = Prompt.inputString("해시 태그(취소 : 엔터) > ");
      if(input.equals("")) {
        System.out.println("해시 태그 검색 취소!");
        return;
      }

      List<Theme> searchedTheme = new ArrayList<>();

      for (Theme theme : themeList) {
        for (String hashtag : theme.getHashtags()) {
          if (hashtag.contains(input) && theme.isPublic()) {
            searchedTheme.add(theme);
          }
        }
      }

      if (searchedTheme.size() == 0) {
        System.out.println("해당하는 테마 없음!");
        continue;
      }

      System.out.printf("[%s]의 검색결과\n", input);
      for (Theme theme : searchedTheme) {
        System.out.printf("테마 제목 > %s\n", theme.getTitle());
      }
      System.out.println();
    }
  }
}