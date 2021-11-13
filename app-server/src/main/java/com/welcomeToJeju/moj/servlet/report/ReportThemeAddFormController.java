package com.welcomeToJeju.moj.servlet.report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/report/themeform")
public class ReportThemeAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "테마 신고하기");
    request.setAttribute("contentUrl", "/report/ReportThemeAddForm.jsp");
    request.getRequestDispatcher("/template_main.jsp").forward(request, response);
  }


}
