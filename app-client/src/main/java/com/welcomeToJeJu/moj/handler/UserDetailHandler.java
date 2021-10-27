package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserDetailHandler implements Command {

  UserDao userDao;

  public UserDetailHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 상세보기]");

    String userNickName = Prompt.inputString("회원 닉네임(취소: 엔터) > ");

    if (userNickName.equals("")) {
      System.out.println("상세보기 취소!");
      return;
    }

    User user = userDao.findByName(userNickName);

    if (user == null) {
      System.out.println("회원 없음!");
      return;
    }

    System.out.printf("이메일 > %s\n", user.getEmail());
    System.out.printf("닉네임 > %s\n", user.getNickName());
    System.out.printf("등록일 > %s\n", user.getRegisteredDate());
    System.out.printf("조회수 > %d\n", user.getViewCount());
    System.out.println();

    request.setAttribute("user", user);
    String input = Prompt.inputString("회원 정보 수정(U) / 회원 삭제 하기(D) > ");
    switch (input) {
      case "U":
      case "u":
        request.getRequestDispatcher("/user/update").forward(request);
        return;
      case "D":
      case "d":
        request.getRequestDispatcher("/user/delete").forward(request);
        return;
      default:
        System.out.println("명령어가 올바르지 않습니다!");
    }
  }
}