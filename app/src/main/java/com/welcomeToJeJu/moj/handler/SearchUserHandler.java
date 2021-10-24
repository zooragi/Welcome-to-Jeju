package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class SearchUserHandler extends AbstractSearchHandler{

  public SearchUserHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {

    System.out.println("[유저 검색 하기]");

    while (true) {
      String input = Prompt.inputString("유저 닉네임(취소 : 엔터) > ");
      if (input.equals("")) {
        System.out.println("유저 검색 취소!");
        return;
      }

      for (User user : userList) {
        if (user.getNickName().contains(input)) {
          System.out.printf("[%s]유저의 테마 목록\n", user.getNickName());
          user.setViewCount(user.getViewCount()+1);

          for (Theme theme : user.getThemeList()) {
            if(!theme.isPublic()) continue;
            System.out.println("테마 제목 > " + theme.getTitle());
          }
          return;
        }
      }
      System.out.println("등록된 유저 없음!");
    }
  }
}