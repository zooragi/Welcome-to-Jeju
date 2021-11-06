package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/addForm")
public class UserAddFormController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/user/UserAddForm.jsp").forward(request, response);
  }

}