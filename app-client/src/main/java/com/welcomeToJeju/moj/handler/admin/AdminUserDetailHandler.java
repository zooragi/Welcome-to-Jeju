package com.welcomeToJeju.moj.handler.admin;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminUserDetailHandler implements Command {

  UserDao userDao;

  public AdminUserDetailHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 상세 보기]");

    String nickName = Prompt.inputString("닉네임(취소 : 엔터) > ");

    if (nickName.equals("") || nickName.length() == 0) {
      System.out.println("🌊 회원 상세 보기 취소!");
      return;
    }

    User user = userDao.findByNickName(nickName);

    if (user == null) {
      System.out.println("🌊 회원 없음!");
      return;
    }

    System.out.printf("이메일 > %s\n", user.getEmail());
    System.out.printf("닉네임 > %s\n", user.getNickName());
    System.out.printf("가입일 > %s\n", user.getRegisteredDate());
    System.out.printf("🚨 경고 > %s\n", user.getWarningCount());

    request.setAttribute("user", user);

    String input = Prompt.inputString("수정하기(U) / 삭제하기(D) > ");

    switch (input) {
      case "U" :
      case "u" :
        request.getRequestDispatcher("/admin/userUpdate").forward(request);
        return;

      case "D" :
      case "d" :
        request.getRequestDispatcher("/admin/userDelete").forward(request);
        return;

      default :
        System.out.println("🌊 수정 / 삭제 실패!");
    }
  }


}
