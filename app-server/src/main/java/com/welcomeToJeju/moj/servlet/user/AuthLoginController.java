package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

//@WebServlet("/auth/login")
public class AuthLoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Cookie cookie = null;
      if (request.getParameter("saveEmail") != null) {
        cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath(getServletContext().getContextPath() + "/auth");
      } else {
        cookie = new Cookie("email", "");
        cookie.setMaxAge(0);
      }
      response.addCookie(cookie);

      User user = userDao.findByEmailAndPassword(email, password);

      if (user != null) {
        request.getSession(true).setAttribute("loginUser", user);
        request.setAttribute("pageTitle", "로그인 성공");
        request.setAttribute("contentUrl", "/user/AuthLogin.jsp");
        request.getRequestDispatcher("/template_main.jsp").forward(request, response);

      } else {
        response.setHeader("Refresh","2;url=loginform");
        request.setAttribute("pageTitle", "로그인 오류");
        request.setAttribute("contentUrl", "/user/LoginFail.jsp");
        request.getRequestDispatcher("/template_main.jsp").forward(request, response);
      }

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }


  }
}
