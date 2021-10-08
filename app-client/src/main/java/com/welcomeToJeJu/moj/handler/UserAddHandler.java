package com.welcomeToJeJu.moj.handler;

import java.sql.Date;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class UserAddHandler implements Command {

  int userNo = 3;
  UserDao userDao;

  public UserAddHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 가입하기]");

    User user = new User();

    String email = Prompt.inputString("이메일 > ");
    if(email.length()==0) {
      System.out.println("회원 가입 취소!");
      return;
    }
    user.setEmail(email);

    String nickname = Prompt.inputString("닉네임 > ");
    if(nickname.length()==0) {
      System.out.println("회원 가입 취소");
      return;
    }
    user.setNickName(nickname);

    user.setPassword(Prompt.inputString("암호 > "));
    user.setRegisteredDate(new Date(System.currentTimeMillis()));
    user.setNo(++userNo);

    userDao.insert(user);
  }

}
