package com.welcomeToJeju.moj.servlet.theme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/theme/userlist")
public class ThemeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;
  UserDao userDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {

      int no = Integer.valueOf(request.getParameter("no"));

      Collection<Theme> themeList = themeDao.findAllPublicThemeByUserNo(no);
      userDao.updateViewCount(no);

      sqlSession.commit();

      request.setAttribute("themeList", themeList);
      request.setAttribute("pageTitle", "테마 리스트");
      request.setAttribute("contentUrl", "/theme/ThemeList.jsp");

      request.getRequestDispatcher("/template_main.jsp").forward(request, response);



    } catch (Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }



}
