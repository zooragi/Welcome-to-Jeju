package com.welcomeToJeju.moj.handler;

import java.util.Collection;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

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
      System.out.printf("회원 이메일 > %s\n", user.getEmail());
      System.out.printf("회원 이름 > %s\n", user.getNickName());
      System.out.printf("가입일 > %s\n", user.getRegisteredDate());
      System.out.printf("조회수 > %d\n", user.getViewCount());
      System.out.println();
    } 
  }
}