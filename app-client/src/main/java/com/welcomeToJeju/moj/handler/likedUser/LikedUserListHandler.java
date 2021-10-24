package com.welcomeToJeju.moj.handler.likedUser;

import java.util.Collection;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class LikedUserListHandler implements Command {

  UserDao userDao;

  public LikedUserListHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유저 좋아요 목록 보기]");

    User loginUser = AuthLoginHandler.getLoginUser();
    Collection<User> likedUserList = userDao.likedUserFindAll(loginUser.getNo());

    if (likedUserList.isEmpty()) {
      System.out.println("🌊 좋아하는 유저 없음!");
      return;
    }

    int index = 1;
    for (User u : likedUserList) {
      System.out.printf("<%d> %s\n", index++, u.getNickName());
    }
  }


}
