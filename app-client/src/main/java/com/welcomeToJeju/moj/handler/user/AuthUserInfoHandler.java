package com.welcomeToJeju.moj.handler.user;

import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AuthUserInfoHandler implements Command{

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[내 정보]");

    User user = AuthLoginHandler.getLoginUser();

    //    if (user == null) {
    //      System.out.println("🌊 내 정보 실패!");
    //      return;
    //    }

    System.out.printf("이메일 > %s\n", user.getEmail());
    System.out.printf("닉네임 > %s\n", user.getNickName());
    System.out.printf("가입일 > %s\n", user.getRegisteredDate());
    System.out.printf("🚨 경고 > %s\n", user.getWarningCount());

    request.setAttribute("loginUser", AuthLoginHandler.getLoginUser());

    String input = Prompt.inputString("수정하기(U) / 탈퇴하기(D) > ");

    switch (input) {
      case "U" :
      case "u" :
        request.getRequestDispatcher("/user/update").forward(request);
        return;

      case "D" :
      case "d" :
        request.getRequestDispatcher("/user/delete").forward(request);
        return;

      default :
        System.out.println("🌊 수정 / 탈퇴 실패!");
    }
  }


}
