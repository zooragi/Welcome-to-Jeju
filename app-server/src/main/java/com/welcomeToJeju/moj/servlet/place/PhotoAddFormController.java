package com.welcomeToJeju.moj.servlet.place;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photo/addform")
public class PhotoAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "사진 등록하기");
    request.setAttribute("contentUrl", "/place/PhotoAddForm.jsp");
  }


}
