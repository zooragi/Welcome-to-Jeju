package com.welcomeToJeju.moj.handler.admin;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminUserUpdateHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public AdminUserUpdateHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 수정하기]");

    User user = (User) request.getAttribute("user");

    String nickname = Prompt.inputString(String.format("닉네임(%s) > ", user.getNickname()));

    int viewCount = Prompt.inputInt(String.format("조회수(%d) > ", user.getViewCount()));
    int warningCount = Prompt.inputInt(String.format("🚨 경고(%d) > ", user.getWarningCount()));

    String input = Prompt.inputString("수정하기(y/N) > ");

    if (input.equalsIgnoreCase("n") | input.length() == 0) {
      System.out.println("회원 수정하기 취소!");
      return;
    }

    user.setNickname(nickname);
    // 조회수, 경고 변경 안 됨
    user.setViewCount(viewCount);
    user.setWarningCount(warningCount);

    userDao.update(user);
    sqlSession.commit();

    System.out.println("회원 수정하기 성공!");
  }


}
