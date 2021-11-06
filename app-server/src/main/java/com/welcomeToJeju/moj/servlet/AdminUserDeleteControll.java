package com.welcomeToJeju.moj.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/admin/delete")
public class AdminUserDeleteControll extends HttpServlet {

  private static final long serialVersionUID = 1L;
  UserDao userDao;
  ThemeDao themeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {

    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      User user = userDao.findByNo(no);

      if (user == null) {
        throw new Exception("회원 없음!<br>");

      } 
      themeDao.deleteAllLikedThemeByUserNo(user.getNo());
      userDao.deleteAllLikedUser(user.getNo());
      userDao.updateActive(user.getNo());
      sqlSession.commit();
      //response.setHeader("Refresh", "1;url=list");
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

}
