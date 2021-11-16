package com.welcomeToJeju.moj.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

//@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      String email = request.getParameter("email");
      String password = request.getParameter("password");

      User user = userDao.findByEmailAndPassword(email, password);

      if (email.equals("root@test.com") && password.equals("0000")) {
        request.getSession(true).setAttribute("loginUser", user);
        request.setAttribute("pageTitle", "로그인 성공");
        request.setAttribute("contentUrl", "/admin/AdminLogin.jsp");
        request.getRequestDispatcher("/template_main.jsp").forward(request, response);

      } else {
        throw new Exception("로그인 실패!");
      }

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }


  }
}
