package com.welcomeToJeju.moj.servlet.likedTheme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/likedtheme/list")
public class LikedThemeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      User loginUser = (User) request.getSession(true).getAttribute("loginUser");

      Collection<Theme> themeList = themeDao.findAllLikedTheme(loginUser.getNo());

      request.setAttribute("themeList", themeList);

      request.setAttribute("pageTitle", "테마 좋아요 목록 보기");
      request.setAttribute("contentUrl", "/likedTheme/LikedThemeList.jsp");
      request.getRequestDispatcher("/template_main.jsp").forward(request, response);

      //      request.getRequestDispatcher("/likedTheme/LikedThemeList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
