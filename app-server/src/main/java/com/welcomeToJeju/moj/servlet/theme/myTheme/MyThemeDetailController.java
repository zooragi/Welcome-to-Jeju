package com.welcomeToJeju.moj.servlet.theme.myTheme;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/theme/myTheme/detail")
public class MyThemeDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Theme theme = themeDao.findByNo(no);
      //      String title = request.getParameter("title");

      if (theme == null) {
        throw new Exception("테마 없음!");
      }

      //      System.out.println(theme);

      request.setAttribute("theme", theme);
      request.getRequestDispatcher("/theme/myTheme/MyThemeDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}