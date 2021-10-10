package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class LikedUserDeleteHandler implements Command {

  UserDao userDao;

  public LikedUserDeleteHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 삭제하기]");

    String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
    if(input.length()==0) {
      System.out.println("좋아요 삭제 취소!");
      return;
    }

    User likedUser = userDao.findByName(input);

    if(likedUser == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }

    String loginUser = AuthLoginHandler.getLoginUser().getNickName();

    userDao.userLikedUserDelete(likedUser.getNickName(), loginUser);
  }


}
