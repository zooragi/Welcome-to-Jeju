package com.welcomeToJeju.moj.servlet.report;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/report/theme")
public class ReportThemeAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ReportDao reportDao;
  ThemeDao themeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reportDao = (ReportDao) 웹애플리케이션공용저장소.getAttribute("reportDao");
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ReportTheme reportTheme = new ReportTheme();

    int no = Integer.parseInt(request.getParameter("no"));
    Theme theme;
    try {
      theme = themeDao.findByNo(no);
      reportTheme.setReportedTheme(theme);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    User loginUser = (User) request.getSession(true).getAttribute("loginUser");
    reportTheme.setWriter(loginUser);

    reportTheme.setContent("content");

    reportTheme.setNo(100);

    try {
      //신고당한 횟수

      reportDao.insertReportTheme(reportTheme);
      sqlSession.commit();

      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("ReportTHemeAdd.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
