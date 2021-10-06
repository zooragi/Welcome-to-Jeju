package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class LikedUserDeleteHandler implements Command {

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로우 삭제하기]");

    String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
    for( User user : AuthLoginHandler.getLoginUser().getLikedUsers()) {
      if(user.getNickName().equals(input)) {
        AuthLoginHandler.getLoginUser().getLikedUsers().remove(user);
        System.out.println("팔로우 취소 완료!");
        break;
      }
    }

  }


}
