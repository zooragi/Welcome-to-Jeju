package com.welcomeToJeJu.moj.handler;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.context.UserContextListener;
import com.welcomeToJeJu.menu.Menu;

public class AuthLogoutHandler implements Command{

  List<UserContextListener> userListeners;

  public AuthLogoutHandler(List<UserContextListener> userListeners) {
    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
    notifyOnLogout();

  }

  private void notifyOnLogout() {
    HashMap<String, Object> params = new HashMap<>();

    for (UserContextListener listener : userListeners) {
      listener.contextLogout(params);
    }
  }


}
