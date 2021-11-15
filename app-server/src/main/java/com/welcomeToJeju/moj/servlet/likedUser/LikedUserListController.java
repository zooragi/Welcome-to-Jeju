package com.welcomeToJeju.moj.servlet.likedUser;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/likeduser/list")
public class LikedUserListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Collection<User> userList = userDao.findAllLikedUser(no);

      request.setAttribute("userList", userList);
      request.setAttribute("pageTitle", "좋아하는 유저 리스트");
      request.setAttribute("contentUrl", "/likedUser/LikedUserList.jsp");

    } catch (Exception e) {
      request.setAttribute("error", e);
    }
  }
}

