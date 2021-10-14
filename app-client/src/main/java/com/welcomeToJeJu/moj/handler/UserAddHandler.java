package com.welcomeToJeJu.moj.handler;

import java.sql.Date;
import java.util.List;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class UserAddHandler implements Command {

  UserDao userDao;

  public UserAddHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 가입하기]");

    List<User> userList = userDao.findAll();

    User user = new User();

    int no = Prompt.inputInt("번호 > ");

    for( User user1 : userList) {
      if( user1.getNo() == no ) {
        System.out.println("중복된 번호!");
        return;
      }
    }
    user.setNo(no);

    String email = Prompt.inputString("이메일 > ");
    if(email.length()==0) {
      System.out.println("회원 가입 취소!");
      return;
    } else if (!email.contains("@")){
      System.out.println("이메일은 '@' 문자를 포함해야 합니다.");
      return;
    }
    user.setEmail(email);

    String nickname = Prompt.inputString("닉네임 > ");
    if(nickname.length()==0) {
      System.out.println("회원 가입 취소");
      return;
    }

    for( User user1 : userList) {
      if( user1.getNickName().equals(nickname)) {
        System.out.println("중복된 닉네임!");
        return;
      }
    }

    user.setNickName(nickname);
    user.setPassword(Prompt.inputString("암호 > "));
    user.setRegisteredDate(new Date(System.currentTimeMillis()));

    userDao.insert(user);

    System.out.println("회원 가입 완료!");
  }

}
