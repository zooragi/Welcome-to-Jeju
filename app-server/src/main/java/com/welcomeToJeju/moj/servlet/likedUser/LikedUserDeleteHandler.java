package com.welcomeToJeju.moj.servlet.likedUser;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/likeduser/delete")
public class LikedUserDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UserDao userDao;
  SqlSession sqlSession;

  @Override
  public void init() {

    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      userDao.deleteLikedUser(
          Integer.parseInt(request.getParameter("no")), 
          ((User)request.getSession(true).getAttribute("loginUser")).getNo());
      sqlSession.commit();
      response.sendRedirect("../theme/list");

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
