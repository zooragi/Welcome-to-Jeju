package com.welcomeToJeju.moj.handler.search;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.ThemePrompt;
import com.welcomeToJeju.util.Prompt;

public class SearchUserHandler implements Command {
  UserDao userDao;
  ThemePrompt themePrompt;

  public SearchUserHandler(UserDao userDao,ThemePrompt themePrompt) {
    this.userDao = userDao;
    this.themePrompt = themePrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[유저 검색 하기]");

    while (true) {
      String input = Prompt.inputString("유저 닉네임(취소 : 엔터) > ");
      User user = userDao.findByName(input);

      if(user == null) {
        System.out.println("등록된 회원 아닙니다");
        continue;
      }
      int currentViewCount = user.getViewCount();
      user.setViewCount(currentViewCount+1);
      userDao.update(user);

      System.out.printf("[%s]유저의 테마 목록\n", user.getNickName());
      themePrompt.printMyList(user);
      System.out.println();

      return;
    }
  }
}