package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class UserUpdateHandler implements Command {

  RequestAgent requestAgent;

  public UserUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 정보 수정하기]");
    int no = Prompt.inputInt("번호 > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("user.selectOne", params);
    User user = requestAgent.getObject(User.class);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    User temp = new User();
    temp.setNo(user.getNo());
    temp.setEmail(Prompt.inputString("이메일 > "));
    temp.setLikedThemes(user.getLikedThemes());
    temp.setLikedUsers(user.getLikedUsers());
    temp.setNickName(Prompt.inputString("닉네임 > "));
    temp.setPassword(Prompt.inputString("암호 > "));
    temp.setRegisteredDate(user.getRegisteredDate());
    temp.setReportedCount(user.getReportedCount());
    temp.setThemeList(user.getThemeList());
    temp.setViewCount(user.getViewCount());
    temp.setWarningCount(user.getWarningCount());


    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 수정 취소!");
      return;
    }

    requestAgent.request("user.update", temp);

    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("회원 수정 완료!");
    } else {
      System.out.println("회원 수정 실패!");
    }
  }

}