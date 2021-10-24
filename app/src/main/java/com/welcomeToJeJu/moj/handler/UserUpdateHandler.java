package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserUpdateHandler extends AbstractUserHandler{
  public UserUpdateHandler(List<User> user) {
    super(user);
  }

  public void execute(CommandRequest request) {
    System.out.println("[회원 정보 수정하기]");
    int no = Prompt.inputInt("번호 > ");

    User user = findByNo(no);

    if (user == null) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    String email = Prompt.inputString("이메일(" + user.getEmail() + ") > ");
    String password = Prompt.inputString("암호 > ");
    String nickName = Prompt.inputString("닉네임(" + user.getNickName() + ") > ");

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 수정 취소!");
      return;
    }

    user.setEmail(email);
    user.setPassword(password);
    user.setNickName(nickName);

    System.out.println("회원 수정 완료!");
  }

}