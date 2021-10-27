package com.welcomeToJeju.moj.handler;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class AdminUserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public AdminUserDeleteHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 삭제하기]"); // 관리자용

    User user = (User) request.getAttribute("user");

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제 취소!");
      return;
    }

    userDao.delete(user.getNo());
    sqlSession.commit();
    System.out.println("회원 삭제 성공!");

  }
}