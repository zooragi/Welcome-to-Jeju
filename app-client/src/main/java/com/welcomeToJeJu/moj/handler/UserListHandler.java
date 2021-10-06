package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;

public class UserListHandler implements Command {

  RequestAgent requestAgent;

  public UserListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록 보기]");

    requestAgent.request("user.selectList", null);
    Collection<User> userList = requestAgent.getObjects(User.class);

    for (User user : userList) {
      System.out.printf("회원 번호 > %s\n", user.getNo());
      System.out.printf("회원 이름 > %s\n", user.getNickName());
      System.out.printf("가입일 > %s\n", user.getRegisteredDate());
      System.out.println();
    } 
  }
}