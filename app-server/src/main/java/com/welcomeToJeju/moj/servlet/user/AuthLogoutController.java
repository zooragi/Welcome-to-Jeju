package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/auth/logout")
public class AuthLogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getSession(true).invalidate();
    request.setAttribute("pageTitle", "로그아웃");
    response.setHeader("Refresh", "1;../auth/loginform");
    request.setAttribute("contentUrl", "/user/AuthLogout.jsp");
    request.getRequestDispatcher("/template_main.jsp").forward(request, response);  
  }
}

