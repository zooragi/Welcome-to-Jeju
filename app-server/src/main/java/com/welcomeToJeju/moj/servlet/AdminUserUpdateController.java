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
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/admin/update")
public class AdminUserUpdateController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  UserDao userDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {

    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      User user = userDao.findByNo(no);

      if (user == null) {
        throw new Exception("해당 번호의 회원이 없습니다!");

      }

      String password = request.getParameter("password");
      String nickname = request.getParameter("nickname");

      user.setPassword(password);
      user.setNickname(nickname);

      userDao.update(user);
      sqlSession.commit();
      // response.setHeader("Refresh", "1;url=list");
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
