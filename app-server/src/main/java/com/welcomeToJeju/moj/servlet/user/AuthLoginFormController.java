package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/auth/loginform")
public class AuthLoginFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "로그인 하기");
    request.setAttribute("contentUrl", "/WEB-INF/jsp/user/AuthLoginForm.jsp");
    request.getRequestDispatcher("/WEB-INF/jsp/template_main.jsp").forward(request, response);
  }


}
