package com.welcomeToJeju.moj.servlet.theme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/theme/detail")
public class ThemeDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;
  //  UserDao userDao;
  PlaceDao placeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    //    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Theme theme = themeDao.findByNo(no);
      themeDao.updateViewCount(theme.getNo());

      sqlSession.commit();

      //      theme.setCategory(themeDao.findCategoryByNo(theme.getCategory().getNo()));
      //      theme.setOwner(userDao.findByNo(theme.getOwner().getNo()));
      Collection<Place> placeList = placeDao.findAllByThemeNo(no);

      request.setAttribute("theme", theme);
      request.setAttribute("placeList", placeList);

      request.getRequestDispatcher("/theme/ThemeDetail.jsp").forward(request, response);
      request.getRequestDispatcher("/place/PlaceList.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
