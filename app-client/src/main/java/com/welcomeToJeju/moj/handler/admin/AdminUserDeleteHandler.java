package com.welcomeToJeju.moj.handler.admin;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminUserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public AdminUserDeleteHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 삭제하기]");

    User user = (User) request.getAttribute("user");

    String input = Prompt.inputString("삭제하기(y/N) > ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제하기 취소!");
      return;
    }

    try {
      // 에러
      userDao.delete(user.getNo());
      userDao.deleteAllLikedUser(user.getNo());
      sqlSession.commit();
      System.out.println("회원 삭제하기 성공!");

    } catch (Exception e) {
      System.out.println(e);
      sqlSession.rollback();
      System.out.println("회원 삭제하기 실패!");
    }

  }


}
