package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.User;

public class LikedUserListHandler extends AbstractUserHandler {

  public LikedUserListHandler(List<User> userList) {
    super(userList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[팔로우 목록보기]");
    if (AuthLoginHandler.getLoginUser().getLikedUsers().size() == 0) {
      System.out.println("팔로우하는 유저 없음!");
      return;
    }

    int index = 1;
    for (User list : AuthLoginHandler.getLoginUser().getLikedUsers()) {
      System.out.printf("<%d> 닉네임 > %s \n", index++, list.getNickName());
    }
  }

}
