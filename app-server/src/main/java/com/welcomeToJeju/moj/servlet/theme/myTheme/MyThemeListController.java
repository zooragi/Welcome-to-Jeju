package com.welcomeToJeju.moj.servlet.theme.myTheme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;


@WebServlet("/theme/myTheme/list")
public class MyThemeListController extends GenericServlet {
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

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Collection<Theme> themeList = themeDao.findByUserNo(no);

      request.setAttribute("myThemeList", themeList);

      request.getRequestDispatcher("/theme/myTheme/MyThemeList.jsp").forward(request, response);

    } catch (Exception e){
      request.setAttribute("error", e);

      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

    //    if (themeList.size() == 0) {
    //      System.out.println("테마 없음!");
    //      return;
    //    }
    //
    //    int no = 1;
    //    for (Theme theme : themeList) {
    //      if (AuthLoginHandler.getLoginUser().getNo() == theme.getOwner().getNo()) {
    //        System.out.printf("<%d>\n", no++);
    //        System.out.printf("제목 > %s\n", theme.getTitle());
    //        System.out.printf("카테고리 > %s\n", theme.getCategory().getName());
    //        System.out.printf("해시태그 > %s\n", theme.getHashtags().toString());
    //        if (theme.getIsPublic() == 1) {
    //          System.out.println("공개 테마");
    //          System.out.printf("조회수 > %s\n", theme.getViewCount());
    //        } else {
    //          System.out.println("비공개 테마");
    //        }
    //          }
    //  }
  }


}
