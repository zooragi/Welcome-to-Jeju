package com.welcomeToJeju.moj.servlet.ranking;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/ranking/user")
public class UserRankingController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<User> userList = userDao.userRankingByViewCount();

      request.setAttribute("userList", userList);

      //      request.setAttribute("pageTitle", "순위 보기");
      //      request.setAttribute("contentUrl", "/ranking/Ranking.jsp");
      request.setAttribute("pageTitle", "유저 순위 보기");
      request.setAttribute("contentUrl", "/ranking/UserRanking.jsp");

      request.getRequestDispatcher("/template_main.jsp").forward(request, response);

      //      request.getRequestDispatcher("/ranking/UserRanking.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
