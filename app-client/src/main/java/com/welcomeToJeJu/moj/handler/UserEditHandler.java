package com.welcomeToJeJu.moj.handler;

import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class UserEditHandler implements Command {

  UserDao userDao;

  public UserEditHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[내 정보 수정하기]");

    User user = (User) request.getAttribute("loginUser");

    User temp = new User();
    temp.setNo(user.getNo());
    temp.setEmail(Prompt.inputString("이메일 > "));
    temp.setLikedUsers(user.getLikedUsers());
    temp.setNickName(Prompt.inputString("닉네임 > "));
    temp.setPassword(Prompt.inputString("암호 > "));
    temp.setRegisteredDate(user.getRegisteredDate());
    temp.setReportedCount(user.getReportedCount());
    temp.setViewCount(user.getViewCount());
    temp.setWarningCount(user.getWarningCount());


    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 수정 취소!");
      return;
    }

    userDao.update(temp);

    System.out.println("회원 수정 완료!");
  }

}