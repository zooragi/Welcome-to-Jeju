package com.welcomeToJeju.moj.servlet.likedTheme;

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
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/likedtheme/delete")
public class LikedThemeDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      User loginUser = (User) request.getSession(true).getAttribute("loginUser");
      int no = Integer.parseInt(request.getParameter("no"));

      themeDao.deleteLikedTheme(no, loginUser.getNo());
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
