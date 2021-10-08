package com.welcomeToJeJu.moj.handler;

import java.util.Collection;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;

public class UserListHandler implements Command {

  UserDao userDao;

  public UserListHandler(UserDao userDao) {
    this.userDao = userDao;
  }


  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록 보기]");

    Collection<User> userList = userDao.findAll();

    for (User user : userList) {
      System.out.printf("회원 번호 > %s\n", user.getNo());
      System.out.printf("회원 이름 > %s\n", user.getNickName());
      System.out.printf("가입일 > %s\n", user.getRegisteredDate());
      System.out.println();
    } 
  }
}