package com.welcomeToJeju.moj.handler.user;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class UserAddHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public UserAddHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 가입]");

    User user = new User();

    user.setEmail(Prompt.inputString("이메일 > "));
    user.setPassword(Prompt.inputString("비밀번호 > "));
    user.setNickName(Prompt.inputString("닉네임 > "));

    userDao.insert(user);
    sqlSession.commit();

    System.out.println("🌊 회원 가입 성공!");
  }


}
