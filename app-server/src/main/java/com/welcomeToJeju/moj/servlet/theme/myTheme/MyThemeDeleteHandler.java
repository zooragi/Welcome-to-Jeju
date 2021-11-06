package com.welcomeToJeju.moj.servlet.theme.myTheme;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/myTheme/delete")
public class MyThemeDeleteHandler extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ThemeDao themeDao;
  PlaceDao placeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {

    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("   <title>테마삭제</title>");
    out.println("</head>");
    out.println(" <body>");
    out.println("<h1>테마삭제결과</h1>");

    try {
      String title = request.getParameter("title");
      Theme theme = themeDao.findByTitle(title);

      if (theme == null) {
        out.println("테마 없음!<br>");
      } else {

        themeDao.deleteAllLikedThemeByThemeNo(theme.getNo());
        themeDao.deleteHashtag(theme.getNo());
        themeDao.deletePlaceUserTheme(theme.getNo());
        themeDao.delete(theme.getNo());
        sqlSession.commit();
        out.println("나의 테마 삭제하기 성공!<br>");
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("<a href='list'>[목록]</a><br>");

    out.println("</body>");
    out.println("</html>");
  }

}
