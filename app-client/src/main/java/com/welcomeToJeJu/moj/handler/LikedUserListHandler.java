package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;

public class LikedUserListHandler implements Command {

  RequestAgent requestAgent;

  public LikedUserListHandler(RequestAgent requestAgent) {
    this.requestAgent =requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로우 목록보기]");

    User loginUser = AuthLoginHandler.getLoginUser();
    requestAgent.request("user.likedUser.list", loginUser);

    Collection<User> likedUserList = requestAgent.getObjects(User.class);
    if (likedUserList.isEmpty()) {
      System.out.println("팔로우하는 유저 없음!");
      return;
    }

    int index = 1;
    for (User user : likedUserList ) {
      System.out.printf("<%d> 닉네임 > %s \n", index++, user.getNickName());
    }
  }

}
