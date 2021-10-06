package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class LikedUserDeleteHandler implements Command {

  RequestAgent requestAgent;

  public LikedUserDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로우 삭제하기]");

    String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
    if(input.length()==0) {
      System.out.println("팔로우 삭제 취소!");
      return;
    }

    HashMap<String, String> params = new HashMap<>();
    params.put("nickname", input);

    requestAgent.request("user.selectOneByName", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 유저 없음!");
      return;
    }
    User likedUser = requestAgent.getObject(User.class);

    String loginUser = AuthLoginHandler.getLoginUser().getNickName();
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("likedUser",likedUser.getNickName());
    parameter.put("loginUser",loginUser);

    requestAgent.request("user.likedUser.delete", parameter);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("팔로우 삭제 완료!");
    } else {
      System.out.println("팔로우 삭제 불가!");
    }


  }


}
