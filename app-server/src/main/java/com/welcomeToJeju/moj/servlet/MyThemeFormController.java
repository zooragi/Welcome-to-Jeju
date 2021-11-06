package com.welcomeToJeju.moj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/theme/myTheme/form")
public class MyThemeFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      request.getRequestDispatcher("/theme/myTheme/MyThemeForm.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }





  }
}







