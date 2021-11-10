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
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/ranking/theme")
public class ThemeRankingController extends HttpServlet {
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
      Collection<Theme> themeList = themeDao.themeRankingByViewCount();

      request.setAttribute("themeList", themeList);
      request.getRequestDispatcher("/ranking/ThemeRanking.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
