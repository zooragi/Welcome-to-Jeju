package com.welcomeToJeju.moj.servlet.theme.myTheme;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mytheme/addform")
public class MyThemeAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "나의 테마 등록하기");
    request.setAttribute("contentUrl", "/theme/myTheme/MyThemeAddForm.jsp");

    request.getRequestDispatcher("/template_main.jsp").forward(request, response);
  }


}
