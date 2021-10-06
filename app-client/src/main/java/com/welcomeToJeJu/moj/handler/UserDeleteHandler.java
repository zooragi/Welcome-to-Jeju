package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class UserDeleteHandler implements Command {

  RequestAgent requestAgent;
  public UserDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 삭제하기]");

    int no = Prompt.inputInt("번호 > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("user.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제 취소!");
      return;
    }

    requestAgent.request("user.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("회원 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }
    System.out.println("회원 삭제 완료!");
  }
}