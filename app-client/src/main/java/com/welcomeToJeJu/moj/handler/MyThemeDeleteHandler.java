package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class MyThemeDeleteHandler implements Command {

  RequestAgent requestAgent;
  public MyThemeDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 삭제하기]");
    String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if(title.equals("")) {
      System.out.println("나의 테마 삭제 취소!");
      return;
    }

    requestAgent.request("theme.selectOneByTitle", title);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    Theme theme = requestAgent.getObject(Theme.class);

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 삭제 취소!");
      return;
    }

    HashMap<String, String> params = new HashMap<>();
    //    params.put("no", String.valueOf(theme.getNo()));
    params.put("title", theme.getTitle());
    requestAgent.request("theme.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("테마 삭제 완료!");
    } else {
      System.out.println("테마 삭제 불가!");
    }
  }

}
