package com.welcomeToJeju.moj.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/admin/loginform")
public class AdminLoginFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "관리자 로그인");
    request.setAttribute("contentUrl", "/admin/AdminLoginForm.jsp");
    request.getRequestDispatcher("/template_main.jsp").forward(request, response);
  }


}
