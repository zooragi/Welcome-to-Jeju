package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.context.UserContextListener;
import com.welcomeToJeJu.menu.Menu;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class AuthLoginHandler implements Command{

  UserDao userDao;

  static User loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;

  List<UserContextListener> userListeners;
  User user;

  public static User getLoginUser() {
    return loginUser;
  }
  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLoginHandler(UserDao userDao, List<UserContextListener> userListeners) {
    this.userDao = userDao;
    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("암호 > ");

    if(email.equals("root@test.com") && password.equals("0000")) {
      User root = new User();
      root.setNickName("제주정승🍊");
      root.setEmail("root@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      System.out.println("제주정승🍊 환영합니다!");
      return;
    }

    User user = userDao.findByEmailPassword(email, password);

    if(user == null) {
      System.out.println("이메일과 암호가 일치하는 회원 없음!");
    } else {
      loginUser = user;
      userAccessLevel = Menu.ACCESS_GENERAL;
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
