package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class UserDeleteHandler implements Command {

  UserDao userDao;

  public UserDeleteHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 삭제하기]");

    int no = Prompt.inputInt("번호 > ");

    User user = userDao.findByNo(no);

    if(user == null) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제 취소!");
      return;
    }

    userDao.delete(user.getNo());

    System.out.println("회원 삭제 완료!");
  }
}