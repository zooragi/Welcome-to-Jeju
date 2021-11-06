package com.welcomeToJeju.moj.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/admin/detail")
public class AdminUserDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {

    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      User user = userDao.findByNo(no);

      if (user == null) {
        throw new Exception("해당 번호의 회원 없음!");
      }

      request.setAttribute("user", user);
      request.getRequestDispatcher("/admin/AdminUserDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

}
