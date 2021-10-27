package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class AuthLoginHandler implements Command{

  UserDao userDao;

  static User loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;
  List<UserContextListener> userListeners = new ArrayList<>();
  User user;

  public static User getLoginUser() {
    return loginUser;
  }
  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLoginHandler(UserDao userDao,List<UserContextListener> userListeners) {
    this.userDao = userDao;
    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) throws Exception{
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("암호 > ");

    user = userDao.findByEmailAndPassword(email, password);

    if(email.equals("root@test.com") && password.equals("0000")) {
      loginUser = user;
      userAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      System.out.println("제주정승🍊 환영합니다!");
      return;
    }


    if(user != null) {
      loginUser = user;
      userAccessLevel = Menu.ACCESS_GENERAL;
    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
      return;
    }
    notifyOnLogin();

  }


  private void notifyOnLogin() {
    HashMap<String,Object> params = new HashMap<>();

    params.put("currentUser", user);

    for (UserContextListener listener : userListeners) {
      listener.contextLogin(params);
    }
  }


}
