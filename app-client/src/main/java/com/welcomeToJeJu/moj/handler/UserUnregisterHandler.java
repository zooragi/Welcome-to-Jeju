package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class UserUnregisterHandler implements Command {

  UserDao userDao;

  public UserUnregisterHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {


    System.out.println("[회원 탈퇴하기]");

    User user = (User) request.getAttribute("loginUser");

    String input = Prompt.inputString("회원 탈퇴하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴 취소!");
      return;
    }

    userDao.delete(user.getNo());

    System.out.println("회원 탈퇴 완료!");
  }
}