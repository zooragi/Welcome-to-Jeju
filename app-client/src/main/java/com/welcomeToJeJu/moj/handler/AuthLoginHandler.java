package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.context.UserContextListener;
import com.welcomeToJeJu.menu.Menu;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class AuthLoginHandler implements Command{

  RequestAgent requestAgent;

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

  public AuthLoginHandler(RequestAgent requestAgent, List<UserContextListener> userListeners) {
    this.requestAgent = requestAgent;
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

    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    requestAgent.request("user.selectOneByEmailPassword", params);
    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      user = requestAgent.getObject(User.class);
      loginUser = user;
      userAccessLevel = Menu.ACCESS_GENERAL;
    } else {
      System.out.println("이메일과 암호가 일치하는 회원 없음!");
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
