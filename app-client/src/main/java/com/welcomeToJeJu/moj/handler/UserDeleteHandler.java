package com.welcomeToJeju.moj.handler;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public UserDeleteHandler(UserDao userDao, SqlSession sqlSession) {
    this.sqlSession = sqlSession;
    this.userDao = userDao;

  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 탈퇴하기]");

    User user = (User) request.getAttribute("loginUser");

    String input = Prompt.inputString("회원 탈퇴하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴 취소!");
      return;
    }

    userDao.delete(user.getNo());
    sqlSession.commit();
    AuthLoginHandler.userAccessLevel=Menu.ACCESS_LOGOUT;
    System.out.println("회원 탈퇴 완료!");
  }
}
