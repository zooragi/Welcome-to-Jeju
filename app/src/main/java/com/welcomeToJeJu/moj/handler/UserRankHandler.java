package com.welcomeToJeju.moj.handler;

import java.util.Collections;
import java.util.List;
import com.welcomeToJeju.moj.domain.User;

public class UserRankHandler implements Command{
  List<User> userList;
  public UserRankHandler(List<User> userList) {
    this.userList = userList;
  }

  public void execute(CommandRequest request) {
    int i = 1;
    System.out.println("[유저 랭킹]");
    Collections.sort(userList);
    for(User user : userList) {
      System.out.printf("%d. %s\n",i,user.getNickName());			
      i++;
    }
  }


}
