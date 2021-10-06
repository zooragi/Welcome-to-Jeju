package com.welcomeToJeJu.moj.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.context.UserContextListener;
import com.welcomeToJeJu.menu.Menu;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.util.Prompt;

public class AuthLoginHandler implements Command{

  List<User> userList;
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

  public AuthLoginHandler(List<User> userList,List<UserContextListener> userListeners) {
    this.userList = userList;
    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) {
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("암호 > ");

    if(email.equals("root@test.com") && password.equals("0000")) {
      loginUser = userList.get(0);
      userAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      System.out.println("제주정승🍊 환영합니다!");
      return;
    }

    user = findByEmailPassword(email, password);

    if (user == null) {
      System.out.println("이메일 또는 암호가 일치하지 않음!");
    } else {
      notifyOnLogin();
      userAccessLevel = Menu.ACCESS_GENERAL;
    }

    loginUser = user;
  }

  private User findByEmailPassword(String email, String password) {
    for (User user : userList) {
      if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  private void notifyOnLogin() {
    HashMap<String,Object> params = new HashMap<>();

    params.put("currentUser", user);

    for (UserContextListener listener : userListeners) {
      listener.contextLogin(params);
    }
  }


}
