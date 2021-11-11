package com.welcomeToJeju.moj.servlet.report;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/report/list")
public class ReportListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ReportDao reportDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reportDao = (ReportDao) 웹애플리케이션공용저장소.getAttribute("reportDao");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      User loginUser = (User) request.getSession(true).getAttribute("loginUser");

      Collection<ReportTheme> themeList = reportDao.reportThemeFindByUserNo(loginUser.getNo());
      Collection<ReportUser> userList = reportDao.reportUserFindByUserNo(loginUser.getNo());

      request.setAttribute("themeList", themeList);
      request.setAttribute("userList", userList);

      request.getRequestDispatcher("/report/ReportList.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e);
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
