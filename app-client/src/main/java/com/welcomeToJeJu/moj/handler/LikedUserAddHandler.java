package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class LikedUserAddHandler implements Command {

  UserDao userDao;

  public LikedUserAddHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로우 등록하기]");
    //    while(true) {
    String input = Prompt.inputString("팔로우 할 유저의 닉네임(취소 : 엔터) > ");
    if(input.length()==0) {
      System.out.println("팔로우 등록 취소!");
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

    if(likedUser == AuthLoginHandler.getLoginUser()) {
      System.out.println("본인은 팔로우 불가!");
      return;
    }

    for(User followUser : AuthLoginHandler.getLoginUser().getLikedUsers()) {
      if(followUser.getNickName().equals(input)) {
        System.out.println("이미 팔로우 한 유저!");
        return;
      }
    }

    String loginUser = AuthLoginHandler.getLoginUser().getNickName();
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("likedUser",likedUser.getNickName());
    parameter.put("loginUser",loginUser);

    requestAgent.request("user.likedUser.insert", parameter);
    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("팔로우 등록 추가!");
    } else {
      System.out.println("팔로우 등록 불가!");
    }
  }
  //  }


}

