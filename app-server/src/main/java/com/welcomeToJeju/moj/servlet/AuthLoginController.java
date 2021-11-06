package com.welcomeToJeju.moj.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/user/login")
public class AuthLoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  UserDao userDao;
  static User loginUser;
  // static int userAccessLevel = Menu.ACCESS_LOGOUT;

  //  List<UserContextListener> userListeners = new ArrayList<>();

  public static User getLoginUser() {
    return loginUser;
  }
  //  public static int getUserAccessLevel() {
  //    return userAccessLevel;
  //  }

  //  public AuthLoginController(UserDao userDao, List<UserContextListener> userListeners) {
  //    this.userDao = userDao;
  //    this.userListeners = userListeners;
  //  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      User user = userDao.findByEmailAndPassword(email, password);

      if (user == null) {
        throw new Exception("로그인 실패!");

      } else {

        request.setAttribute("user", user);
        request.getRequestDispatcher("/user/UserLogin.jsp").forward(request, response);

      }
    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

    //    userAccessLevel = Menu.ACCESS_GENERAL;

    //    if(email.equals("root@test.com") && password.equals("0000")) {
    //      loginUser = user;
    //      userAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
    //      return;
    //    }
    //
    //    notifyOnLogin();
    //  }

    //  private void notifyOnLogin() {
    //    HashMap<String,Object> params = new HashMap<>();
    //
    //    params.put("currentUser", user);
    //
    //    for (UserContextListener listener : userListeners) {
    //      listener.contextLogin(params);
    //    }
    //  }

  }
}
