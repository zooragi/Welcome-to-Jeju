package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class UserDetailHandler implements Command {

  RequestAgent requestAgent;

  public UserDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 상세보기]"); // 관리자용

    int no = Prompt.inputInt("번호 > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("user.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    User user = requestAgent.getObject(User.class);

    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("닉네임: %s\n", user.getNickName());
    System.out.printf("등록일: %s\n", user.getRegisteredDate());
  }
}